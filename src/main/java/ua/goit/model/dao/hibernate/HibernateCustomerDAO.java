package ua.goit.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.model.dao.CustomerDAO;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateCustomerDAO extends JdbcDBConnection implements CustomerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateCustomerDAO.class);

    private static HibernateCustomerDAO instance;

    private static SessionFactory sessionFactory;

    private HibernateCustomerDAO() {
    }

    public static HibernateCustomerDAO getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateCustomerDAO();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Customer> read(Long key) {
        try(Session session = getSessionFactory().openSession()){
            Customer customer = session.get(Customer.class, key);
            if (customer != null) {
                return Optional.of(customer);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void create(Customer customer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving customer", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Customer customer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.update(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating customer", e.getMessage()));
            }
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.delete(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting customer", e.getMessage()));
            }
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            try {
                customerList = session.createQuery("FROM Customer").list();
                System.out.println("Hibernate get All!!!");
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all customers", e.getMessage()));
            }
        }
        return customerList;
    }

    public void createCustomerProject(Long customerId, String projectName, int projectCost) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Customer customer = session.get(Customer.class, customerId);
                List<Project> projectList = customer.getProjects();
                Project project = new Project();
                project.setProjectName(projectName);
                project.setProjectCost(projectCost);
                projectList.add(project);
                session.save(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while creating project for customer", e.getMessage()));
            }
        }
    }
}
