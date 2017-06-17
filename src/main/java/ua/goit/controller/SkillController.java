package ua.goit.controller;

import ua.goit.model.dao.jdbc.JdbcSkillDAO;
import ua.goit.model.entity.Skill;

import java.util.List;
import java.util.Optional;

public class SkillController implements Controller<Skill, Long> {

    private JdbcSkillDAO jdbcSkillDAO = JdbcSkillDAO.getInstance();

    @Override
    public Optional<Skill> read(Long key) {
        return jdbcSkillDAO.read(key);
    }

    @Override
    public void create(Skill skill) {
        jdbcSkillDAO.create(skill);
    }

    @Override
    public void update(Skill skill) {
        jdbcSkillDAO.update(skill);
    }

    @Override
    public void delete(Skill skill) {
        jdbcSkillDAO.delete(skill);
    }

    @Override
    public List<Skill> getAll() {
        return jdbcSkillDAO.getAll();
    }
}
