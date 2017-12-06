package ua.goit.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column (name = "COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "companies_customers",
            joinColumns = @JoinColumn(name = "COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID")
    )
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
