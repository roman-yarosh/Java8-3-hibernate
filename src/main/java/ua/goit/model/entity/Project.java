package ua.goit.model.entity;

import java.util.List;

public class Project {

    private Long projectId;
    private String projectName;
    private int projectCost;
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
