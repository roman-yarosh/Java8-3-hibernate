package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.SkillDAO;
import ua.goit.model.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSkillDAO extends JdbcDBConnection implements SkillDAO {


    private static JdbcSkillDAO instance;

    private JdbcSkillDAO() {
    }

    public static JdbcSkillDAO getInstance() {
        if (instance == null) {
            instance = new JdbcSkillDAO();
        }
        return instance;
    }

    private static final String READ_ALL_SKILL_SQL = "select SKILL_ID, SKILL_NAME from pm.skills";

    private static final String READ_SKILL_SQL = READ_ALL_SKILL_SQL + " where SKILL_ID = ?";

    private static final String CREATE_SKILL_SQL = "insert into pm.skills(SKILL_NAME) values (?)";

    private static final String UPDATE_SKILL_SQL = "update pm.skills set SKILL_NAME = ? where SKILL_ID = ?";

    private static final String DELETE_SKILL_SQL = "delete from pm.skills where SKILL_ID = ?";

    @Override
    public Optional<Skill> read(Long key) {
        try (Connection connection = getConnection()) {
            Skill skill;
            try (PreparedStatement statement = connection.prepareStatement(READ_SKILL_SQL)) {
                statement.setLong(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    skill = getSkill(set);
                }
            }
            return Optional.of(skill);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Skill skill) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_SKILL_SQL)) {
                statement.setString(1, skill.getSkillName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Skill skill) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SKILL_SQL)) {
                statement.setString(1, skill.getSkillName());
                statement.setLong(2, skill.getSkillId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Skill skill) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SKILL_SQL)) {
                statement.setLong(1, skill.getSkillId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_SKILL_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Skill skill = getSkill(set);
                        skillList.add(skill);
                    }
                }
            }
            return skillList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Skill getSkill(ResultSet set) throws SQLException {
        Skill skill = new Skill();
        skill.setSkillId(set.getLong("SKILL_ID"));
        skill.setSkillName(set.getString("SKILL_NAME"));
        return skill;
    }
}
