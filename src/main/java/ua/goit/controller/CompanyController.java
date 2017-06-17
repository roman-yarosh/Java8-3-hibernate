package ua.goit.controller;

import ua.goit.model.dao.jdbc.JdbcCompanyDAO;
import ua.goit.model.entity.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyController implements Controller<Company, Long>{

    JdbcCompanyDAO jdbcCompanyDAO = new JdbcCompanyDAO();

    @Override
    public Optional<Company> read(Long key) {
        return Optional.empty();
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

    public List<Company> getAll(){
        List<Company> companyList = new ArrayList<Company>();
        return companyList = jdbcCompanyDAO.getAll();
    }
}
