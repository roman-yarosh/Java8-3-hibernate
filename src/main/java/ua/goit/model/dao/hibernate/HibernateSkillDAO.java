package ua.goit.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.model.dao.SkillDAO;
import ua.goit.model.entity.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateSkillDAO implements SkillDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSkillDAO.class);

    private static HibernateSkillDAO instance;

    private static SessionFactory sessionFactory;

    private HibernateSkillDAO() {
    }

    public static HibernateSkillDAO getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateSkillDAO();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Skill> read(Long key) {
        try(Session session = getSessionFactory().openSession()){
            Skill skill = session.get(Skill.class, key);
            if (skill != null) {
                return Optional.of(skill);
            } else {
                return Optional.empty();
            }
        }    }

    @Override
    public void create(Skill skill) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(skill);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving skill", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Skill skill) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.update(skill);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating skill", e.getMessage()));
            }
        }

    }

    @Override
    public void delete(Skill skill) {
        try (Session session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.delete(skill);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting skill", e.getMessage()));
            }
        }

    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            try {
                skillList = session.createQuery("FROM Skill").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all skills", e.getMessage()));
            }
        }
        return skillList;
    }
}
