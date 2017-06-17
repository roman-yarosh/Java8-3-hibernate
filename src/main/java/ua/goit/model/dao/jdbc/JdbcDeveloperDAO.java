package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDeveloperDAO extends JdbcDBConnection implements DeveloperDAO {

    private static JdbcDeveloperDAO instance;

    private JdbcDeveloperDAO() {
    }

    public static JdbcDeveloperDAO getInstance() {
        if (instance == null) {
            instance = new JdbcDeveloperDAO();
        }
        return instance;
    }

    private static final String READ_ALL_SQL = "select DEVELOPER_ID, NAME, EXPERIENCE, SALARY from pm.developers";

    private static final String READ_SQL = READ_ALL_SQL + " where DEVELOPER_ID = ?";

    private static final String CREATE_SQL = "insert into pm.developers(NAME, EXPERIENCE, SALARY) " +
            "values (?, ?, ?)";

    private static final String UPDATE_SQL = "update pm.developers set NAME = ?," +
            " EXPERIENCE = ?, SALARY = ? where DEVELOPER_ID = ?";

    private static final String DELETE_SQL = "delete from pm.developers where DEVELOPER_ID = ?";

    private static final String SELECT_SKILLS_SQL = "select SKILL_ID, SKILL_NAME from pm.skills where SKILL_ID in " +
            "(select SKILL_ID from pm.developers_skills where DEVELOPER_ID = ?) ";

    private static final String CREATE_SKILL_SQL = "insert into pm.skills(SKILL_NAME) values (?)";

    private static final String CREATE_DEVELOPER_SKILL_SQL = "insert into pm.developers_skills(DEVELOPER_ID, SKILL_ID)" +
            " values (?, ?)";

    @Override
    public void create(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
                statement.setString(1, developer.getName());
                statement.setInt(2, developer.getExperience());
                statement.setInt(3, developer.getSalary());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                statement.setString(1, developer.getName());
                statement.setInt(2, developer.getExperience());
                statement.setInt(3, developer.getSalary());
                statement.setLong(4, developer.getDeveloperId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
                statement.setLong(1, developer.getDeveloperId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Developer> read(Long key) {
        try (Connection connection = getConnection()) {
            Developer developer;
            try (PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
                statement.setLong(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    developer = getDeveloper(set);
                }
            }

            List<Skill> skills = getSkillsForDeveloper(key, connection);
            developer.setSkills(skills);
            return Optional.of(developer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Skill> getSkillsForDeveloper(Long developerId, Connection connection) throws SQLException {
        List<Skill> skills = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SKILLS_SQL)) {
            statement.setLong(1, developerId);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Skill skill = new Skill();
                    skill.setSkillId(set.getLong("SKILL_ID"));
                    skill.setSkillName(set.getString("SKILL_NAME"));
                    skills.add(skill);
                }
            }
        }
        return skills;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Developer developer = getDeveloper(set);
                        List<Skill> skills = getSkillsForDeveloper(developer.getDeveloperId(), connection);
                        developer.setSkills(skills);
                        developerList.add(developer);
                    }
                }
            }
            return developerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Developer getDeveloper(ResultSet set) throws SQLException {

        Developer developer = new Developer();
        developer.setDeveloperId(set.getLong("DEVELOPER_ID"));
        developer.setName(set.getString("NAME"));
        developer.setExperience(set.getInt("EXPERIENCE"));
        developer.setSalary(set.getInt("SALARY"));
        return developer;
    }

    public void createDeveloperSkills(Long developerID, String skillName) {

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(CREATE_SKILL_SQL, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, skillName);
                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new RuntimeException("Inserting developer skill, no rows affected!");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        Long skillId =  generatedKeys.getLong(1);
                        try (PreparedStatement statement2 = connection.prepareStatement(CREATE_DEVELOPER_SKILL_SQL)){
                            statement2.setLong(1, developerID);
                            statement2.setLong(2, skillId);
                            if (statement2.executeUpdate() == 0) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                                throw new RuntimeException("Inserting developer skill, no rows affected!");
                            }
                        }
                    }
                    else {
                        connection.rollback();
                        throw new RuntimeException("Inserting developer skill failed, no ID obtained!");
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