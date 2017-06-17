package ua.goit.model.dao.jdbc;

import ua.goit.model.dao.CompanyDAO;
import ua.goit.model.entity.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCompanyDAO implements CompanyDAO{

    @Override
    public Optional<Company> read(Long key) {
        return null;
    }

    @Override
    public void create(Company entity) {

    }

    @Override
    public void update(Company entity) {

    }

    @Override
    public void delete(Company entity) {

    }

    @Override
    public List<Company> getAll() {
        return new ArrayList<Company>();
    }
}
