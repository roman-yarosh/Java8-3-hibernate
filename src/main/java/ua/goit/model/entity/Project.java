package ua.goit.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "COST")
    private Integer projectCost;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "developers_projects",
            joinColumns = @JoinColumn(name = "PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "DEVELOPER_ID")
    )
    private List<Developer> developers;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("projectId=").append(projectId);
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append(", projectCost=").append(projectCost);
        sb.append(", developers=").append(developers);
        sb.append('}');
        return sb.toString();
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }
}
