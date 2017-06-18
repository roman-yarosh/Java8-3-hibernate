package ua.goit.view;

import ua.goit.controller.DeveloperController;
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
            case 1:
                writeMessage("Enter new " + DeveloperFields.NAME.getFieldName() + ":");
                name = readString();
                writeMessage("Enter new " + DeveloperFields.EXPERIENCE.getFieldName() + ":");
                experience = readInt();
                writeMessage("Enter new " + DeveloperFields.SALARY.getFieldName() + ":");
                salary = readInt();

                developer.setName(name);
                developer.setExperience(experience);
                developer.setSalary(salary);

                developerController.create(developer);
                writeMessage("Success!");
                break;
            case 2:
                writeMessage("Enter " + DeveloperFields.DEVELOPER_ID.getFieldName() + ":");
                developerId = readLong();

                writeMessage(developerController.read(developerId).toString());
                writeMessage("Success!");
                break;
            case 3:
                writeMessage("Enter " + DeveloperFields.DEVELOPER_ID.getFieldName() + " for update:");
                developerId = readLong();
                writeMessage("Enter new " + DeveloperFields.NAME.getFieldName() + ":");
                name = readString();
                writeMessage("Enter new " + DeveloperFields.EXPERIENCE.getFieldName() + ":");
                experience = readInt();
                writeMessage("Enter new " + DeveloperFields.SALARY.getFieldName() + ":");
                salary = readInt();

                developer.setDeveloperId(developerId);
                developer.setName(name);
                developer.setExperience(experience);
                developer.setSalary(salary);

                developerController.update(developer);
                writeMessage("Success!");
                break;
            case 4:
                writeMessage("Enter " + DeveloperFields.DEVELOPER_ID.getFieldName() + " for delete:");
                developerId = readLong();

                developer.setDeveloperId(developerId);

                developerController.delete(developer);
                writeMessage("Success!");
                break;
            case 5:
                writeMessage("Developers list:");
                developerController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case 6:
                writeMessage("Enter " + DeveloperFields.DEVELOPER_ID.getFieldName() + ":");
                developerId = readLong();
                writeMessage("Enter new " + SkillFields.SKILL_NAME.getFieldName() + ":");
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
