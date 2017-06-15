package ua.goit.model.dao;

import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDeveloperDAO implements DeveloperDAO {

    private static final String READ_SQL = "select DEVELOPER_ID, NAME, EXPERIENCE, SALARY " +
            "from pm.developers where DEVELOPER_ID = ?";
    private static final String CREATE_SQL = "insert into pm.developers(DEVELOPER_ID, NAME, EXPERIENCE, SALARY) " +
            "values (?, ?, ?, ?)";

    private static final String UPDATE_SQL = "update pm.developers set DEVELOPER_ID = ?, NAME = ?," +
            " EXPERIENCE = ?, SALARY = ? where DEVELOPER_ID = ?";
    private static final String DELETE_SQL = " delete from pm.developers where DEVELOPER_ID = ?";

    private static final String SELECT_SKILLS_SQL = "select SKILL_ID, SKILL_NAME from pm.skills where SKILL_ID in " +
            " (select SKILL_ID from pm.developers_skills where DEVELOPER_ID = ?) ";

    @Override
    public Optional<Developer> read(Integer key) {
        try (Connection connection = getConnection()) {
            Developer developer = null;
            try (PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
                statement.setInt(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    developer = new Developer();
                    developer.setDeveloperId(key);
                    developer.setName(set.getString("NAME"));
                    developer.setExperience(set.getInt("EXPERIENCE"));
                    developer.setSalary(set.getInt("SALARY"));
                }
            }
            List<Skills> skills = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(SELECT_SKILLS_SQL)) {
                statement.setInt(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Skills skill = new Skills();
                        skill.setSkillId(set.getInt("SKILL_ID"));
                        skill.setSkillName(set.getString("SKILL_NAME"));
                        skills.add(skill);
                    }
                }
            }
            developer.setSkills(skills);
            return Optional.of(developer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
                statement.setInt(1, developer.getDeveloperId());
                statement.setString(2, developer.getName());
                statement.setInt(3, developer.getExperience());
                statement.setInt(3, developer.getSalary());
                statement.executeUpdate();

                //add skills !!!!!!!!!!!!!!!!!!!!!!!!!
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                statement.setInt(1, developer.getDeveloperId());
                statement.setString(2, developer.getName());
                statement.setInt(3, developer.getExperience());
                statement.setInt(3, developer.getSalary());
                statement.executeUpdate();
                //add skills update
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Developer developer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
                statement.setInt(1, developer.getDeveloperId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pm";
        // -Dusername=XXX -Dpassword=YYYY
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }
}