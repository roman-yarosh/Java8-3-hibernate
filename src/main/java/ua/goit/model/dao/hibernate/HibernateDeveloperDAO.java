package ua.goit.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateDeveloperDAO implements DeveloperDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDeveloperDAO.class);

    private static HibernateDeveloperDAO instance;

    private static SessionFactory sessionFactory;

    private HibernateDeveloperDAO() {
    }

    public static HibernateDeveloperDAO getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateDeveloperDAO();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Developer> read(Long key) {
        try(Session session = getSessionFactory().openSession()){
            Developer developer = session.get(Developer.class, key);
            if (developer != null) {

                return Optional.of(developer);
            } else {
                return Optional.empty();
            }
        }
    }


    @Override
    public void create(Developer developer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving developer", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Developer developer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.update(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating developer", e.getMessage()));
            }
        }

    }

    @Override
    public void delete(Developer developer) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.delete(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting developer", e.getMessage()));
            }
        }

    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            try {
                developerList = session.createQuery("FROM Developer").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all developers", e.getMessage()));
            }
        }
        return developerList;

    }

    public void createDeveloperSkills(Long developerID, String skillName) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                Developer developer = session.get(Developer.class, developerID);
                List<Skill> skillList = developer.getSkills();
                Skill skill = new Skill();
                skill.setSkillName(skillName);
                skillList.add(skill);
                session.save(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while creating skill for developer", e.getMessage()));
            }
        }
    }
}