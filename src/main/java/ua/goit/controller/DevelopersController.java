package ua.goit.controller;

import ua.goit.model.dao.JdbcDeveloperDAO;
import ua.goit.model.entity.Developer;
import java.util.Optional;

public class DevelopersController {

    private JdbcDeveloperDAO jdbcDeveloperDAO = JdbcDeveloperDAO.getInstance();

    public void create(Developer developer){
        jdbcDeveloperDAO.create(developer);
    }

    public void update(Developer developer) {
        jdbcDeveloperDAO.update(developer);
    }

    public void delete(Developer developer) {
        jdbcDeveloperDAO.delete(developer);
    }

    public Optional<Developer> read(Integer key) {
        return jdbcDeveloperDAO.read(key);
    }
}
