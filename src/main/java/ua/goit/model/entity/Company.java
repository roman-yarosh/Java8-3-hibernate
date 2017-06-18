package ua.goit.model.entity;

import java.util.List;

public class Company {

    private Long companyId;
    private String companyName;
    private String companyAddress;
    private List<Customer> customers;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("companyId=").append(companyId);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", companyAddress='").append(companyAddress).append('\'');
        sb.append(", customers=").append(customers);
        sb.append('}');
        return sb.toString();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
