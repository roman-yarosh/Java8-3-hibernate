package ua.goit.model.dao;

import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDeveloperDAO extends JdbcDBConnection implements DeveloperDAO {

    private static final String READ_ALL_SQL = "select DEVELOPER_ID, NAME, EXPERIENCE, SALARY from pm.developers";

    private static final String READ_SQL = READ_ALL_SQL + " where DEVELOPER_ID = ?";

    private static final String CREATE_SQL = "insert into pm.developers(DEVELOPER_ID, NAME, EXPERIENCE, SALARY) " +
            "values (?, ?, ?, ?)";

    private static final String UPDATE_SQL = "update pm.developers set DEVELOPER_ID = ?, NAME = ?," +
            " EXPERIENCE = ?, SALARY = ? where DEVELOPER_ID = ?";

    private static final String DELETE_SQL = "delete from pm.developers where DEVELOPER_ID = ?";

    private static final String SELECT_SKILLS_SQL = "select SKILL_ID, SKILL_NAME from pm.skills where SKILL_ID in " +
            "(select SKILL_ID from pm.developers_skills where DEVELOPER_ID = ?) ";

    @Override
    public void create(Developer developer) {
        executeSQL(developer, CREATE_SQL, false);
    }

    @Override
    public void update(Developer developer) {
        executeSQL(developer, UPDATE_SQL, false);
    }

    @Override
    public void delete(Developer developer) {
        executeSQL(developer, DELETE_SQL, true);
    }

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
                    developer = getDeveloper(set);
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

    private void executeSQL(Developer developer, String sql, boolean forDelete) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, developer.getDeveloperId());
                if (!forDelete) {
                    statement.setString(2, developer.getName());
                    statement.setInt(3, developer.getExperience());
                    statement.setInt(3, developer.getSalary());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        Developer developer = null;
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        developer = getDeveloper(set);
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
        developer.setDeveloperId(set.getInt("DEVELOPER_ID"));
        developer.setName(set.getString("NAME"));
        developer.setExperience(set.getInt("EXPERIENCE"));
        developer.setSalary(set.getInt("SALARY"));
        return developer;
    }
}