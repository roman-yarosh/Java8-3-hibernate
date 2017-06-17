package ua.goit.controller;

import ua.goit.model.dao.jdbc.JdbcProjectDAO;
import ua.goit.model.entity.Project;

import java.util.List;
import java.util.Optional;

public class ProjectController implements Controller<Project, Long>{

    private JdbcProjectDAO jdbcProjectDAO = new JdbcProjectDAO();

    @Override
    public Optional<Project> read(Long key) {
        return jdbcProjectDAO.read(key);
    }

    @Override
    public void create(Project project) {
        jdbcProjectDAO.create(project);
    }

    @Override
    public void update(Project project) {
        jdbcProjectDAO.update(project);
    }

    @Override
    public void delete(Project project) {
        jdbcProjectDAO.delete(project);
    }

    @Override
    public List<Project> getAll() {
        return jdbcProjectDAO.getAll();
    }
}
