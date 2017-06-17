package ua.goit.controller;

import ua.goit.model.dao.jdbc.JdbsCustomerDAO;
import ua.goit.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerController implements Controller<Customer, Long> {

    private JdbsCustomerDAO jdbsCustomerDAO = new JdbsCustomerDAO();

    @Override
    public Optional<Customer> read(Long key) {
        return jdbsCustomerDAO.read(key);
    }

    @Override
    public void create(Customer customer) {
        jdbsCustomerDAO.create(customer);
    }

    @Override
    public void update(Customer customer) {
        jdbsCustomerDAO.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        jdbsCustomerDAO.delete(customer);
    }

    @Override
    public List<Customer> getAll() {
        return jdbsCustomerDAO.getAll();
    }
}
