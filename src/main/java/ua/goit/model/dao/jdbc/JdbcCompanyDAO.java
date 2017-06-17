package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.CompanyDAO;
import ua.goit.model.entity.Company;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCompanyDAO extends JdbcDBConnection implements CompanyDAO{

    private static JdbcCompanyDAO instance;

    private JdbcCompanyDAO() {
    }

    public static JdbcCompanyDAO getInstance() {
        if (instance == null) {
            instance = new JdbcCompanyDAO();
        }
        return instance;
    }

    private static final String READ_ALL_COMPANIES_SQL = "select COMPANY_ID, COMPANY_NAME, COMPANY_ADDRESS from pm.companies";

    private static final String READ_COMPANY_SQL = READ_ALL_COMPANIES_SQL + " where COMPANY_ID = ?";

    private static final String CREATE_COMPANY_SQL = "insert into pm.companies(COMPANY_NAME, COMPANY_ADDRESS) " +
            "values (?, ?)";

    private static final String UPDATE_COMPANY_SQL = "update pm.companies set COMPANY_NAME = ?," +
            " COMPANY_ADDRESS = ? where COMPANY_ID = ?";

    private static final String DELETE_COMPANY_SQL = "delete from pm.companies where COMPANY_ID = ?";

    private static final String SELECT_COMPANY_CUSTOMERS_SQL = "select CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ADDRESS from pm.customers " +
            "where CUSTOMER_ID in (select CUSTOMER_ID from pm.companies_customers where COMPANY_ID = ?) ";

    private static final String CREATE_CUSTOMER_SQL = "insert into pm.customers(CUSTOMER_NAME, CUSTOMER_ADDRESS)" +
            " values (?, ?)";

    private static final String CREATE_COMPANY_CUSTOMER_SQL = "insert into pm.companies_customers(COMPANY_ID, CUSTOMER_ID)" +
            " values (?, ?)";

    @Override
    public Optional<Company> read(Long key) {
        try (Connection connection = getConnection()) {
            Company company;
            try (PreparedStatement statement = connection.prepareStatement(READ_COMPANY_SQL)) {
                statement.setLong(1, key);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) {
                        return Optional.empty();
                    }
                    company = getCompany(set);
                }
            }

            List<Customer> customers = getCustomersForCompanies(key, connection);
            company.setCustomers(customers);
            return Optional.of(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Customer> getCustomersForCompanies(Long companyId, Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_COMPANY_CUSTOMERS_SQL)) {
            statement.setLong(1, companyId);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(set.getLong("CUSTOMER_ID"));
                    customer.setCustomerName(set.getString("CUSTOMER_NAME"));
                    customer.setCustomerAddress(set.getString("CUSTOMER_ADDRESS"));
                    customers.add(customer);
                }
            }
        }
        return customers;
    }
    @Override
    public void create(Company company) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_COMPANY_SQL)) {
                statement.setString(1, company.getCompanyName());
                statement.setString(2, company.getCompanyAddress());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Company company) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_COMPANY_SQL)) {
                statement.setString(1, company.getCompanyName());
                statement.setString(2, company.getCompanyAddress());
                statement.setLong(3, company.getCompanyId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Company company) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY_SQL)) {
                statement.setLong(1, company.getCompanyId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(READ_ALL_COMPANIES_SQL)) {
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        Company company = getCompany(set);
                        List<Customer> customers = getCustomersForCompanies(company.getCompanyId(), connection);
                        company.setCustomers(customers);
                        companyList.add(company);
                    }
                }
            }
            return companyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Company getCompany(ResultSet set) throws SQLException {

        Company company = new Company();
        company.setCompanyId(set.getLong("COMPANY_ID"));
        company.setCompanyName(set.getString("COMPANY_NAME"));
        company.setCompanyAddress(set.getString("COMPANY_ADDRESS"));
        return company;
    }

    public void createCompanyCustomer(Long companyId, String customerName, String customerAddress) {

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customerName);
                statement.setString(2, customerAddress);
                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new RuntimeException("Inserting company customer, no rows affected!");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        Long customerId =  generatedKeys.getLong(1);
                        try (PreparedStatement statement2 = connection.prepareStatement(CREATE_COMPANY_CUSTOMER_SQL)){
                            statement2.setLong(1, companyId);
                            statement2.setLong(2, customerId);
                            if (statement2.executeUpdate() == 0) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                                throw new RuntimeException("Inserting company customer, no rows affected!");
                            }
                        }
                    }
                    else {
                        connection.rollback();
                        throw new RuntimeException("Inserting company customer failed, no ID obtained!");
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
