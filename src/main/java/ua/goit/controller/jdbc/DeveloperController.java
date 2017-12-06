package ua.goit.controller.jdbc;

import ua.goit.controller.Controller;
import ua.goit.model.dao.jdbc.JdbcDeveloperDAO;
import ua.goit.model.entity.Developer;
import java.util.List;
import java.util.Optional;

public class DeveloperController implements Controller<Developer, Long> {

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

    public List<Developer> getAll() {
        return jdbcDeveloperDAO.getAll();
    }

    public void createDeveloperSkills(Long developerId, String skillName) {
        jdbcDeveloperDAO.createDeveloperSkills(developerId, skillName);
    }
}

