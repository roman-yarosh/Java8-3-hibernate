package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.CustomerDAO;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerDAO extends JdbcDBConnection implements CustomerDAO {

    private static JdbcCustomerDAO instance;

    private JdbcCustomerDAO() {
    }

    public static JdbcCustomerDAO getInstance() {
        if (instance == null) {
            instance = new JdbcCustomerDAO();
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
    public Optional<Customer> read(Long key) {
        try (Connection connection = getConnection()) {
            Customer customer;
            try (PreparedStatement statement = connection.prepareStatement(READ_CUSTOMER_SQL)) {
                statement.setLong(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    customer = getCustomer(set);
                }
            }

            List<Project> projects = getProjectsForCustomers(key, connection);
            customer.setProjects(projects);
            return Optional.of(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Customer getCustomer(ResultSet set) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(set.getLong("CUSTOMER_ID"));
        customer.setCustomerName(set.getString("CUSTOMER_NAME"));
        customer.setCustomerAddress(set.getString("CUSTOMER_ADDRESS"));
        return customer;
    }

    private List<Project> getProjectsForCustomers(Long customerId, Connection connection) throws SQLException {
        List<Project> projects = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_PROJECTS_SQL)) {
            statement.setLong(1, customerId);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Project project = new Project();
                    project.setProjectId(set.getLong("PROJECT_ID"));
                    project.setProjectName(set.getString("PROJECT_NAME"));
                    project.setProjectCost(set.getInt("COST"));
                    projects.add(project);
                }
            }
        }
        return projects;
    }

    @Override
    public void create(Customer customer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER_SQL)) {
                statement.setString(1, customer.getCustomerName());
                statement.setString(2, customer.getCustomerAddress());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
                statement.setString(1, customer.getCustomerName());
                statement.setString(2, customer.getCustomerAddress());
                statement.setLong(3, customer.getCustomerId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
                statement.setLong(1, customer.getCustomerId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_CUSTOMER_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Customer customer = getCustomer(set);
                        List<Project> projects = getProjectsForCustomers(customer.getCustomerId(), connection);
                        customer.setProjects(projects);
                        customerList.add(customer);
                    }
                }
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCustomerProject(Long customerId, String projectName, int projectCost) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(CREATE_PROJECT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, projectName);
                statement.setInt(2, projectCost);
                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new RuntimeException("Inserting customer project, no rows affected!");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        Long projectId =  generatedKeys.getLong(1);
                        try (PreparedStatement statement2 = connection.prepareStatement(CREATE_CUSTOMER_PROJECT_SQL)){
                            statement2.setLong(1, customerId);
                            statement2.setLong(2, projectId);
                            if (statement2.executeUpdate() == 0) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                                throw new RuntimeException("Inserting customer project, no rows affected!");
                            }
                        }
                    }
                    else {
                        connection.rollback();
                        throw new RuntimeException("Inserting customer project failed, no ID obtained!");
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
