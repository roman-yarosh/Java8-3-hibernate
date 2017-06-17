package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.ProjectDAO;
import ua.goit.model.entity.Project;

import java.util.List;
import java.util.Optional;

public class JdbcProjectDAO implements ProjectDAO {

    private static JdbcProjectDAO instance;

    private JdbcProjectDAO() {
    }

    public static JdbcProjectDAO getInstance() {
        if (instance == null) {
            instance = new JdbcProjectDAO();
        }
        return instance;
    }

    @Override
    public Optional<Project> read(Long key) {
        return null;
    }

    @Override
    public void create(Project entity) {

    }

    @Override
    public void update(Project entity) {

    }

    @Override
    public void delete(Project entity) {

    }

    @Override
    public List<Project> getAll() {
        return null;
    }
}
