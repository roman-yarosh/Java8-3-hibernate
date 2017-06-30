package ua.goit.controller.hibernate;

import ua.goit.controller.Controller;
import ua.goit.model.dao.hibernate.HibernateCustomerDAO;
import ua.goit.model.entity.Customer;
import ua.goit.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCustomerController implements Controller<Customer, Long> {

    private HibernateCustomerDAO hibernateCustomerDAO = HibernateCustomerDAO.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Customer> read(Long key) {
        return hibernateCustomerDAO.read(key);
    }

    @Override
    public void create(Customer customer) {
        hibernateCustomerDAO.create(customer);
    }

    @Override
    public void update(Customer customer) {
        hibernateCustomerDAO.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        hibernateCustomerDAO.delete(customer);
    }

    @Override
    public List<Customer> getAll() {
        return hibernateCustomerDAO.getAll();
    }

    public void createCustomerProject(Long customerId, String projectName, int projectCost) {
        hibernateCustomerDAO.createCustomerProject(customerId, projectName, projectCost);
    }
}
