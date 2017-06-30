package ua.goit.view;

import ua.goit.controller.jdbc.DeveloperController;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.DeveloperFields;
import ua.goit.model.entity.SkillFields;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class DeveloperView {

    public void developerViewStarter() throws IOException {

        Developer developer = new Developer();
        DeveloperController developerController = new DeveloperController();

        Long developerId;
        String name;
        int experience;
        int salary;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, 6 - Add skills, any other - Exit to main menu:");
        int userChoice = readInt();
        switch (userChoice) {
            case CREATE:
                writeMessage(String.format("Enter new %s:", DeveloperFields.NAME.getFieldName()));
                name = readString();
                writeMessage(String.format("Enter new %s:", DeveloperFields.EXPERIENCE.getFieldName()));
                experience = readInt();
                writeMessage(String.format("Enter new %s:", DeveloperFields.SALARY.getFieldName()));
                salary = readInt();

                developer.setName(name);
                developer.setExperience(experience);
                developer.setSalary(salary);

                developerController.create(developer);
                writeMessage("Success!");
                break;
            case READ:
                writeMessage(String.format("Enter %s:", DeveloperFields.DEVELOPER_ID.getFieldName()));
                developerId = readLong();

                writeMessage(developerController.read(developerId).toString());
                writeMessage("Success!");
                break;
            case UPDATE:
                writeMessage(String.format("Enter %s for update:", DeveloperFields.DEVELOPER_ID.getFieldName()));
                developerId = readLong();
                writeMessage(String.format("Enter new %s:", DeveloperFields.NAME.getFieldName()));
                name = readString();
                writeMessage(String.format("Enter new %s:", DeveloperFields.EXPERIENCE.getFieldName()));
                experience = readInt();
                writeMessage(String.format("Enter new %s:", DeveloperFields.SALARY.getFieldName()));
                salary = readInt();

                developer.setDeveloperId(developerId);
                developer.setName(name);
                developer.setExperience(experience);
                developer.setSalary(salary);

                developerController.update(developer);
                writeMessage("Success!");
                break;
            case DELETE:
                writeMessage(String.format("Enter %s for delete:", DeveloperFields.DEVELOPER_ID.getFieldName()));
                developerId = readLong();

                developer.setDeveloperId(developerId);

                developerController.delete(developer);
                writeMessage("Success!");
                break;
            case SHOWALL:
                writeMessage("Developers list:");
                developerController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case ADD:
                writeMessage(String.format("Enter %s:", DeveloperFields.DEVELOPER_ID.getFieldName()));
                developerId = readLong();
                writeMessage(String.format("Enter new %s:", SkillFields.SKILL_NAME.getFieldName()));
                name = readString();

                developerController.createDeveloperSkills(developerId, name);
                break;
            default:
                writeMessage("Exit to main menu!");
                ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
                consoleViewStarter.startApp();
                break;
        }
        developerViewStarter();
    }
    }
