package ua.goit.model.entity;

import java.util.List;

public class Customer {

    private Long customerId;
    private String customerName;
    private String customerAddress;
    private List<Project> projects;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("customerId=").append(customerId);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", customerAddress='").append(customerAddress).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
