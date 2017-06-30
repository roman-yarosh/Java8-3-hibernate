package ua.goit.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.model.dao.CompanyDAO;
import ua.goit.model.entity.Company;
import ua.goit.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateCompanyDAO implements CompanyDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateCompanyDAO.class);

    private static HibernateCompanyDAO instance;

    private static SessionFactory sessionFactory;

    private HibernateCompanyDAO() {
    }

    public static HibernateCompanyDAO getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateCompanyDAO();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Company> read(Long key) {
        try(Session session = getSessionFactory().openSession()){
            Company company = session.get(Company.class, key);
            if (company != null) {
                return Optional.of(company);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void create(Company company) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving company", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Company company) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.update(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating company", e.getMessage()));
            }
        }
    }

    @Override
    public void delete(Company company) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.delete(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting company", e.getMessage()));
            }
        }

    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            try {
                companyList = session.createQuery("FROM Company").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all companies", e.getMessage()));
            }
        }
        return companyList;
    }

    public void createCompanyCustomer(Long companyId, String customerName, String customerAddress) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Company company = session.get(Company.class, companyId);
                List<Customer> customerList = company.getCustomers();
                Customer customer = new Customer();
                customer.setCustomerName(customerName);
                customer.setCustomerAddress(customerAddress);
                customerList.add(customer);
                session.save(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while creating customer for company", e.getMessage()));
            }
        }
    }
}
