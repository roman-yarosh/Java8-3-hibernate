package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.SkillDAO;
import ua.goit.model.entity.Skill;

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

    private static final String READ_ALL_CUSTOMER_SQL = "select CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ADDRESS from pm.customers";

    private static final String READ_CUSTOMER_SQL = READ_ALL_CUSTOMER_SQL + " where CUSTOMER_ID = ?";

    private static final String CREATE_CUSTOMER_SQL = "insert into pm.customers(CUSTOMER_NAME, CUSTOMER_ADDRESS) " +
            "values (?, ?)";

    private static final String UPDATE_CUSTOMER_SQL = "update pm.customers set CUSTOMER_NAME = ?," +
            " CUSTOMER_ADDRESS = ? where CUSTOMER_ID = ?";

    private static final String DELETE_CUSTOMER_SQL = "delete from pm.customers where CUSTOMER_ID = ?";

    private static final String SELECT_CUSTOMER_PROJECTS_SQL = "select PROJECT_ID, PROJECT_NAME, COST from pm.projects " +
            "where PROJECT_ID in (select PROJECT_ID from pm.customers_projects where CUSTOMER_ID = ?) ";

    private static final String CREATE_PROJECT_SQL = "insert into pm.projects(PROJECT_NAME, COST)" +
            " values (?, ?)";

    private static final String CREATE_CUSTOMER_PROJECT_SQL = "insert into pm.customers_projects(CUSTOMER_ID, PROJECT_ID)" +
            " values (?, ?)";


    @Override
    public Optional<Skill> read(Long key) {
        return null;
    }

    @Override
    public void create(Skill entity) {

    }

    @Override
    public void update(Skill entity) {

    }

    @Override
    public void delete(Skill entity) {

    }

    @Override
    public List<Skill> getAll() {
        return null;
    }
}
