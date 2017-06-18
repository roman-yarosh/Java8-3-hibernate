package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.ProjectDAO;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProjectDAO extends JdbcDBConnection implements ProjectDAO {

    private static JdbcProjectDAO instance;

    private JdbcProjectDAO() {
    }

    public static JdbcProjectDAO getInstance() {
        if (instance == null) {
            instance = new JdbcProjectDAO();
        }
        return instance;
    }

    private static final String READ_ALL_PROJECT_SQL = "select PROJECT_ID, PROJECT_NAME, COST from pm.projects";

    private static final String READ_PROJECT_SQL = READ_ALL_PROJECT_SQL + " where PROJECT_ID = ?";

    private static final String CREATE_PROJECT_SQL = "insert into pm.projects(PROJECT_NAME, COST) " +
            "values (?, ?)";

    private static final String UPDATE_PROJECT_SQL = "update pm.projects set PROJECT_NAME = ?," +
            " COST = ? where PROJECT_ID = ?";

    private static final String DELETE_PROJECT_SQL = "delete from pm.projects where PROJECT_ID = ?";

    private static final String SELECT_DEVELOPER_PROJECTS_SQL = "select DEVELOPER_ID, NAME, EXPERIENCE, SALARY from pm.developers " +
            "where DEVELOPER_ID in (select DEVELOPER_ID from pm.developers_projects where PROJECT_ID = ?) ";

    private static final String CREATE_DEVELOPER_SQL = "insert into pm.developers(NAME, EXPERIENCE, SALARY)" +
            " values (?, ?, ?)";

    private static final String CREATE_DEVELOPERS_PROJECT_SQL = "insert into pm.developers_projects(DEVELOPER_ID, PROJECT_ID)" +
            " values (?, ?)";

    @Override
    public Optional<Project> read(Long key) {
        try (Connection connection = getConnection()) {
            Project project;
            try (PreparedStatement statement = connection.prepareStatement(READ_PROJECT_SQL)) {
                statement.setLong(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    project = getProject(set);
                }
            }

            List<Developer> developers = getDevelopersForProjects(key, connection);
            project.setDevelopers(developers);
            return Optional.of(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Project getProject(ResultSet set) throws SQLException {
        Project project = new Project();
        project.setProjectId(set.getLong("PROJECT_ID"));
        project.setProjectName(set.getString("PROJECT_NAME"));
        project.setProjectCost(set.getInt("COST"));
        return project;
    }

    private List<Developer> getDevelopersForProjects(Long projectId, Connection connection) throws SQLException {
        List<Developer> developers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DEVELOPER_PROJECTS_SQL)) {
            statement.setLong(1, projectId);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Developer developer = new Developer();
                    developer.setDeveloperId(set.getLong("DEVELOPER_ID"));
                    developer.setName(set.getString("NAME"));
                    developer.setExperience(set.getInt("EXPERIENCE"));
                    developer.setSalary(set.getInt("SALARY"));
                    developers.add(developer);
                }
            }
        }
        return developers;
    }

    @Override
    public void create(Project project) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_PROJECT_SQL)) {
                statement.setString(1, project.getProjectName());
                statement.setInt(2, project.getProjectCost());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Project project) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT_SQL)) {
                statement.setString(1, project.getProjectName());
                statement.setInt(2, project.getProjectCost());
                statement.setLong(3, project.getProjectId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Project project) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_SQL)) {
                statement.setLong(1, project.getProjectId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_PROJECT_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Project project = getProject(set);
                        List<Developer> developers = getDevelopersForProjects(project.getProjectId(), connection);
                        project.setDevelopers(developers);
                        projectList.add(project);
                    }
                }
            }
            return projectList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createProjectDeveloper(Long projectId, String developerName, int developerExperience, int developerSalary) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(CREATE_DEVELOPER_SQL, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, developerName);
                statement.setInt(2, developerExperience);
                statement.setInt(3, developerSalary);
                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new RuntimeException("Inserting project developer, no rows affected!");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        Long developerId =  generatedKeys.getLong(1);
                        try (PreparedStatement statement2 = connection.prepareStatement(CREATE_DEVELOPERS_PROJECT_SQL)){
                            statement2.setLong(1, developerId);
                            statement2.setLong(2, projectId);
                            if (statement2.executeUpdate() == 0) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                                throw new RuntimeException("Inserting project developer, no rows affected!");
                            }
                        }
                    }
                    else {
                        connection.rollback();
                        throw new RuntimeException("Inserting project developer failed, no ID obtained!");
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
