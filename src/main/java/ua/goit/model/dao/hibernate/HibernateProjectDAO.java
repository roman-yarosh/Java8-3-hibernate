package ua.goit.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.model.dao.ProjectDAO;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateProjectDAO implements ProjectDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateProjectDAO.class);

    private static HibernateProjectDAO instance;

    private static SessionFactory sessionFactory;

    private HibernateProjectDAO() {
    }


    public static HibernateProjectDAO getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateProjectDAO();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Project> read(Long key) {
        try(Session session = getSessionFactory().openSession()){
            Project project = session.get(Project.class, key);
            if (project != null) {
                return Optional.of(project);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void create(Project project) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving project", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Project project) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.update(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating project", e.getMessage()));
            }
        }

    }

    @Override
    public void delete(Project project) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.delete(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting project", e.getMessage()));
            }
        }

    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            try {
                projectList = session.createQuery("FROM Project").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all projects", e.getMessage()));
            }
        }
        return projectList;

    }

    public void createProjectDeveloper(Long projectId, String developerName, int developerExperience, int developerSalary) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Project project = session.get(Project.class, projectId);
                List<Developer> developerList = project.getDevelopers();
                Developer developer = new Developer();
                developer.setName(developerName);
                developer.setExperience(developerExperience);
                developer.setSalary(developerSalary);
                developerList.add(developer);
                session.save(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while creating developer for project", e.getMessage()));
            }
        }
    }
}
