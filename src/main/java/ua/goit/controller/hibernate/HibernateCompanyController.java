package ua.goit.controller.hibernate;

import ua.goit.controller.Controller;
import ua.goit.model.dao.hibernate.HibernateCompanyDAO;
import ua.goit.model.entity.Company;
import ua.goit.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCompanyController implements Controller<Company, Long> {

    private HibernateCompanyDAO hibernateCompanyDAO = HibernateCompanyDAO.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Company> read(Long key) {
        return hibernateCompanyDAO.read(key);
    }

    @Override
    public void create(Company company) {
        hibernateCompanyDAO.create(company);
    }

    @Override
    public void update(Company company) {
        hibernateCompanyDAO.update(company);
    }

    @Override
    public void delete(Company company) {
        hibernateCompanyDAO.delete(company);
    }

    @Override
    public List<Company> getAll(){
        return hibernateCompanyDAO.getAll();
    }

    public void createCompanyCustomer(Long companyId, String customerName, String customerAddress) {
        hibernateCompanyDAO.createCompanyCustomer(companyId, customerName, customerAddress);
    }
}
