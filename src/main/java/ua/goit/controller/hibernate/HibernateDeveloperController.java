package ua.goit.controller.hibernate;

import ua.goit.controller.Controller;
import ua.goit.model.dao.hibernate.HibernateDeveloperDAO;
import ua.goit.model.entity.Developer;
import ua.goit.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateDeveloperController implements Controller<Developer, Long> {

    private HibernateDeveloperDAO hibernateDeveloperDAO = HibernateDeveloperDAO.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Developer> read(Long key) {
        return hibernateDeveloperDAO.read(key);
    }

    @Override
    public void create(Developer developer) {
        hibernateDeveloperDAO.create(developer);
    }

    @Override
    public void update(Developer developer) {
        hibernateDeveloperDAO.update(developer);
    }

    @Override
    public void delete(Developer developer) {
        hibernateDeveloperDAO.delete(developer);
    }

    public List<Developer> getAll() {
        return hibernateDeveloperDAO.getAll();
    }

    public void createDeveloperSkills(Long developerId, String skillName) {
        hibernateDeveloperDAO.createDeveloperSkills(developerId, skillName);
    }
}

