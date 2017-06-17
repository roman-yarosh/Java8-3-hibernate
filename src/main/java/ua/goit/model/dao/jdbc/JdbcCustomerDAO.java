package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.CustomerDAO;
import ua.goit.model.entity.Customer;

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

    @Override
    public Optional<Customer> read(Long key) {
        return null;
    }

    @Override
    public void create(Customer entity) {

    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
