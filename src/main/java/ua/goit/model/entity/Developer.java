package ua.goit.model.entity;

import java.util.List;

public class Developer {

    private Long developerId;
    private String name;
    private int experience;
    private int salary;
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
