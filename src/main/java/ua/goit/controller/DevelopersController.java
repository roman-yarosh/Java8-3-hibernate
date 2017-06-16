package ua.goit.controller;

import ua.goit.model.dao.JdbcDeveloperDAO;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.Skill;

import java.util.List;
import java.util.Optional;

public class DevelopersController implements Controller<Developer, Long> {

    private JdbcDeveloperDAO jdbcDeveloperDAO = JdbcDeveloperDAO.getInstance();

    @Override
    public Optional<Developer> read(Long key) {
        return jdbcDeveloperDAO.read(key);
    }

    @Override
    public void create(Developer developer) {
        jdbcDeveloperDAO.create(developer);
    }

    @Override
    public void update(Developer developer) {
        jdbcDeveloperDAO.update(developer);
    }

    @Override
    public void delete(Developer developer) {
        jdbcDeveloperDAO.delete(developer);
    }

    public List<Developer> getAllDevelopers() {
        return jdbcDeveloperDAO.getAllDevelopers();
    }

    public void createDeveloperSkills(Long developerId, String skillName) {
        jdbcDeveloperDAO.createDeveloperSkills(developerId, skillName);
    }
}

