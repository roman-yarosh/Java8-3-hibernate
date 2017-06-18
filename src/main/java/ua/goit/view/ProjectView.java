package ua.goit.view;

import ua.goit.controller.ProjectController;
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
        int userChoise = readInt();
        switch (userChoise) {
            case 1:
                writeMessage("Enter new " + ProjectFields.PROJECT_NAME.getFieldName() + ":");
                projectName = readString();
                writeMessage("Enter new " + ProjectFields.PROJECT_COST.getFieldName() + ":");
                projectCost = readInt();

                project.setProjectName(projectName);
                project.setProjectCost(projectCost);

                projectController.create(project);
                writeMessage("Success!");
                break;
            case 2:
                writeMessage("Enter " + ProjectFields.PROJECT_ID.getFieldName() + ":");
                projectId = readLong();

                writeMessage(projectController.read(projectId).toString());
                writeMessage("Success!");
                break;
            case 3:
                writeMessage("Enter " + ProjectFields.PROJECT_ID.getFieldName() + " for update:");
                projectId = readLong();
                writeMessage("Enter new " + ProjectFields.PROJECT_NAME.getFieldName() + ":");
                projectName = readString();
                writeMessage("Enter new " + ProjectFields.PROJECT_COST.getFieldName() + ":");
                projectCost = readInt();

                project.setProjectId(projectId);
                project.setProjectName(projectName);
                project.setProjectCost(projectCost);

                projectController.update(project);
                writeMessage("Success!");
                break;
            case 4:
                writeMessage("Enter " + ProjectFields.PROJECT_ID.getFieldName() + " for delete:");
                projectId = readLong();

                project.setProjectId(projectId);

                projectController.delete(project);
                writeMessage("Success!");
                break;
            case 5:
                writeMessage("Projects list:");
                projectController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case 6:
                writeMessage("Enter " + ProjectFields.PROJECT_ID.getFieldName() + ":");
                projectId = readLong();
                writeMessage("Enter new " + DeveloperFields.NAME.getFieldName() + ":");
                String developerName = readString();
                writeMessage("Enter new " + DeveloperFields.EXPERIENCE.getFieldName() + ":");
                int developerExperience = readInt();
                writeMessage("Enter new " + DeveloperFields.SALARY.getFieldName() + ":");
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
