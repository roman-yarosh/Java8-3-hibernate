package ua.goit.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @Column(name = "DEVELOPER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long developerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXPERIENCE")
    private Integer experience;

    @Column(name = "SALARY")
    private Integer salary;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "developers_skills",
            joinColumns = @JoinColumn(name = "DEVELOPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SKILL_ID")
    )
    private List<Skill> skills;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Developer{");
        sb.append("developerId=").append(developerId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", experience=").append(experience);
        sb.append(", salary=").append(salary);
        sb.append(", skills=").append(skills);
        sb.append('}');
        return sb.toString();
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
