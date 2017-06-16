package ua.goit.view;

import ua.goit.controller.DevelopersController;
import ua.goit.model.entity.*;

import java.util.Scanner;

public class ConsoleView {

    public static void showCrudDialod() {
        Scanner in = new Scanner(System.in);
        int inputEntity = 10;
        int inputCRUD = 10;
        do {
            showSelectTables();
            try {
                inputEntity = in.nextInt();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Wrong input! Please enter correct integer value!");
                continue;
            }

            System.out.println("Selected entity: " + TableNames.Companies.getAnyTableNameByNum(inputEntity));
            if (inputEntity > 0) do {
                System.out.println("Select CRUD operation: 0 - Exit, 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowDevelopers:");
                try {
                    inputCRUD = in.nextInt();
                } catch (Exception e) {
                    in.nextLine();
                    System.out.println("Wrong input! Please enter correct integer value!");
                    continue;
                }
                System.out.println("Selected entity: " + inputEntity + " and operation: " + inputCRUD);
                if (inputCRUD > 0 && inputCRUD < 6) scannEntity(in, inputEntity, inputCRUD);
            } while (inputCRUD > 0);
        } while (inputEntity > 0);
    }

    private static void showSelectTables() {
        System.out.print("Select entity: 0 - Exit");
        for (TableNames tableName : TableNames.values()) {
            System.out.print(", " + tableName.getTableNum() + " - " + tableName);
        }
        System.out.println(":");
    }

    private static void scannEntity(Scanner in, int inputEntity, int inputCRUD) {
        DevelopersController developersController = new DevelopersController();
        String devName = "";
        Long devExperience = (long) -1;
        Long devSalary = (long) -1;

        if (inputCRUD == 5) {
            developersController.getAllDevelopers().forEach(System.out::println);
        }

        if (inputCRUD == 1) {


            String entityName = TableNames.Companies.getAnyTableNameByNum(inputEntity);
            if (entityName.length() > 0 && inputCRUD < 5) {
                switch (entityName) {
                    case "Developers":
                        devName = getConsoleString(in, DeveloperFields.NAME.getFieldName(), devName);
                        devExperience = getConsoleLong(in, DeveloperFields.EXPERIENCE.getFieldName(), devExperience);
                        devSalary = getConsoleLong(in, DeveloperFields.SALARY.getFieldName(), devSalary);

                        Developer developer = new Developer();
                        developer.setName(devName);
                        developer.setExperience(devExperience.intValue());
                        developer.setSalary(devSalary.intValue());
                        developersController.create(developer);
                        break;
                    case "Companies":
                        break;
                    case "Skills":
                        String skillName = "";
                        Long developerId = (long) -1;
                        developerId = getConsoleLong(in, DeveloperFields.DEVELOPER_ID.getFieldName(), developerId);
                        skillName = getConsoleString(in, SkillFields.SKILL_NAME.getFieldName(), skillName);
                        developersController.createDeveloperSkills(developerId, skillName);
                    default:
                        break;
                }
            } else if (inputCRUD < 5)
                System.out.println("Wrong input: " + inputEntity + "! Please enter correct integer value!");
        }
    }

    private static Long getConsoleLong(Scanner in, String fieldName, Long input) {
        do {
            System.out.println("Enter " + fieldName + ":");
            try {
                input = in.nextLong();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Wrong input! Please enter correct integer value!");
            }
        } while (input == -1);
        return input;
    }

    private static String getConsoleString(Scanner in, String filedName, String input) {
        do {
            System.out.println("Enter " + filedName + ":");
            try {
                in.nextLine();
                input = in.nextLine();
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Wrong input! Please enter correct string value!");
            }
        } while (input.length() <= 0);
        return input;
    }
}
