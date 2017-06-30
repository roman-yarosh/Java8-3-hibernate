package ua.goit.controller.hibernate;

import ua.goit.controller.Controller;
import ua.goit.model.dao.hibernate.HibernateProjectDAO;
import ua.goit.model.entity.Project;
import ua.goit.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateProjectController implements Controller<Project, Long> {

    private HibernateProjectDAO hibernateProjectDAO = HibernateProjectDAO.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Project> read(Long key) {
        return hibernateProjectDAO.read(key);
    }

    @Override
    public void create(Project project) {
        hibernateProjectDAO.create(project);
    }

    @Override
    public void update(Project project) {
        hibernateProjectDAO.update(project);
    }

    @Override
    public void delete(Project project) {
        hibernateProjectDAO.delete(project);
    }

    @Override
    public List<Project> getAll() {
        return hibernateProjectDAO.getAll();
    }

    public void createProjectDeveloper(Long projectId, String developerName, int developerExperience, int developerSalary) {
        hibernateProjectDAO.createProjectDeveloper(projectId, developerName, developerExperience, developerSalary);
    }
}
