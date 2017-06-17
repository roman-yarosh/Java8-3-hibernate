package ua.goit.controller;

import ua.goit.model.dao.jdbc.JdbcCompanyDAO;
import ua.goit.model.entity.Company;

import java.util.List;
import java.util.Optional;

public class CompanyController implements Controller<Company, Long>{

    private JdbcCompanyDAO jdbcCompanyDAO = JdbcCompanyDAO.getInstance();

    @Override
    public Optional<Company> read(Long key) {
        return jdbcCompanyDAO.read(key);
    }

    @Override
    public void create(Company company) {
        jdbcCompanyDAO.create(company);
    }

    @Override
    public void update(Company company) {
        jdbcCompanyDAO.update(company);
    }

    @Override
    public void delete(Company company) {
        jdbcCompanyDAO.delete(company);
    }

    @Override
    public List<Company> getAll(){
        return jdbcCompanyDAO.getAll();
    }
}
