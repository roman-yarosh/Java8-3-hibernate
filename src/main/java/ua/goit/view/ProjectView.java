package ua.goit.view;

import ua.goit.controller.jdbc.ProjectController;
import ua.goit.model.entity.DeveloperFields;
import ua.goit.model.entity.Project;
import ua.goit.model.entity.ProjectFields;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class ProjectView {

    public void projectViewStarter() throws IOException {

        Project project = new Project();
        ProjectController projectController = new ProjectController();

        Long projectId;
        String projectName;
        int projectCost;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, 6 - Add developer, any other - Exit to main menu:");
        int userChoice = readInt();
        switch (userChoice) {
            case CREATE:
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_NAME.getFieldName()));
                projectName = readString();
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_COST.getFieldName()));
                projectCost = readInt();

                project.setProjectName(projectName);
                project.setProjectCost(projectCost);

                projectController.create(project);
                writeMessage("Success!");
                break;
            case READ:
                writeMessage(String.format("Enter %s:", ProjectFields.PROJECT_ID.getFieldName()));
                projectId = readLong();

                writeMessage(projectController.read(projectId).toString());
                writeMessage("Success!");
                break;
            case UPDATE:
                writeMessage(String.format("Enter %s for update:", ProjectFields.PROJECT_ID.getFieldName()));
                projectId = readLong();
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_NAME.getFieldName()));
                projectName = readString();
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_COST.getFieldName()));
                projectCost = readInt();

                project.setProjectId(projectId);
                project.setProjectName(projectName);
                project.setProjectCost(projectCost);

                projectController.update(project);
                writeMessage("Success!");
                break;
            case DELETE:
                writeMessage(String.format("Enter %s for delete:", ProjectFields.PROJECT_ID.getFieldName()));
                projectId = readLong();

                project.setProjectId(projectId);

                projectController.delete(project);
                writeMessage("Success!");
                break;
            case SHOWALL:
                writeMessage("Projects list:");
                projectController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case ADD:
                writeMessage(String.format("Enter %s:", ProjectFields.PROJECT_ID.getFieldName()));
                projectId = readLong();
                writeMessage(String.format("Enter new %s:", DeveloperFields.NAME.getFieldName()));
                String developerName = readString();
                writeMessage(String.format("Enter new %s:", DeveloperFields.EXPERIENCE.getFieldName()));
                int developerExperience = readInt();
                writeMessage(String.format("Enter new %s:", DeveloperFields.SALARY.getFieldName()));
                int developerSalary = readInt();

                projectController.createProjectDeveloper(projectId, developerName, developerExperience, developerSalary);
                break;
            default:
                writeMessage("Exit to main menu!");
                ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
                consoleViewStarter.startApp();
                break;
        }
        projectViewStarter();
    }
}
