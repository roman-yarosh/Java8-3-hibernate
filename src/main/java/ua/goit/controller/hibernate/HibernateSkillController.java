package ua.goit.controller.hibernate;

import ua.goit.controller.Controller;
import ua.goit.model.dao.hibernate.HibernateSkillDAO;
import ua.goit.model.entity.Skill;
import ua.goit.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateSkillController implements Controller<Skill, Long> {

    private HibernateSkillDAO hibernateSkillDAO = HibernateSkillDAO.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Skill> read(Long key) {
        return hibernateSkillDAO.read(key);
    }

    @Override
    public void create(Skill skill) {
        hibernateSkillDAO.create(skill);
    }

    @Override
    public void update(Skill skill) {
        hibernateSkillDAO.update(skill);
    }

    @Override
    public void delete(Skill skill) {
        hibernateSkillDAO.delete(skill);
    }

    @Override
    public List<Skill> getAll() {
        return hibernateSkillDAO.getAll();
    }
}
