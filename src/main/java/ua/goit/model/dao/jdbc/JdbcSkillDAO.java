package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.SkillDAO;
import ua.goit.model.entity.Skill;

import java.util.List;
import java.util.Optional;

public class JdbcSkillDAO implements SkillDAO {
    @Override
    public Optional<Skill> read(Long key) {
        return null;
    }

    @Override
    public void create(Skill entity) {

    }

    @Override
    public void update(Skill entity) {

    }

    @Override
    public void delete(Skill entity) {

    }

    @Override
    public List<Skill> getAll() {
        return null;
    }
}
