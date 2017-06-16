package ua.goit.view;

import ua.goit.controller.DevelopersController;
import ua.goit.model.entity.Developer;
import ua.goit.model.entity.DeveloperFields;
import ua.goit.model.entity.TableNames;

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

            System.out.println("Selected entity: " + inputEntity);
            if (inputEntity > 0) do {
                System.out.println("Select CRUD operation: 0 - Exit, 1 - Create, 2 - Read, 3 - Update, 4 - Delete:");
                try {
                    inputCRUD = in.nextInt();
                } catch (Exception e) {
                    in.nextLine();
                    System.out.println("Wrong input! Please enter correct integer value!");
                    continue;
                }
                System.out.println("Selected entity: " + inputEntity + " and operation: " + inputCRUD);
                if (inputCRUD > 0 && inputCRUD < 5) scannEntity(in, inputEntity, inputCRUD);
            } while (inputCRUD > 0);
        } while (inputEntity > 0);
    }

    private static void showSelectTables() {
        System.out.print("Select entity: 0 - Exit");
        for (TableNames tableName : TableNames.values()){
            System.out.print(", " + tableName.getTableNum() + " - " + tableName);
        }
        System.out.println(":");
    }

    private static void scannEntity(Scanner in, int inputEntity, int inputCRUD) {
        DevelopersController developersController = new DevelopersController();
        String devName = "";
        int devId = 0;
        int devExperience = 0;
        int devSalary = 0;

        String entityName = TableNames.Companies.getAnyTableNameByNum(inputEntity);
        if (entityName.length() > 0) {

            System.out.println("Start to scan.");

            switch (entityName){
                case "Developers" :
                    do {
                        System.out.println("0 - Exit. Enter " + DeveloperFields.DEVELOPER_ID.getFieldName() + ":");
                        try {
                            devId = in.nextInt();
                        } catch (Exception e) {
                            in.nextLine();
                            System.out.println("Wrong input! Please enter correct integer value!");
                        }
                    } while (devId < 1);
                    do {
                        System.out.println("0 - Exit. Enter " + DeveloperFields.NAME.getFieldName() + ":");
                        try {
                            in.nextLine();
                            devName = in.nextLine();
                        } catch (Exception e) {
                            in.nextLine();
                            System.out.println("Wrong input! Please enter correct string value!");
                        }
                    } while (devName.length() <= 0);
                    do {
                        System.out.println("0 - Exit. Enter " + DeveloperFields.EXPERIENCE.getFieldName() + ":");
                        try {
                            devExperience = in.nextInt();
                        } catch (Exception e) {
                            in.nextLine();
                            System.out.println("Wrong input! Please enter correct integer value!");
                        }
                    } while (devExperience < 1);
                    do {
                        System.out.println("0 - Exit. Enter " + DeveloperFields.SALARY.getFieldName() + ":");
                        try {
                            devSalary = in.nextInt();
                        } catch (Exception e) {
                            in.nextLine();
                            System.out.println("Wrong input! Please enter correct integer value!");
                        }
                    } while (devSalary < 1);

                    Developer developer = new Developer();
                    developer.setDeveloperId(devId);
                    developer.setName(devName);
                    developer.setExperience(devExperience);
                    developer.setSalary(devSalary);
                    developersController.create(developer);
                    break;
                case "Companies" : break;
                default: break;
            }

            //System.out.println("FieldNames: " + DeveloperFields.valueOf("DEVELOPER_ID").getFieldName());
        } else System.out.println("Wrong input: " + inputEntity + "! Please enter correct integer value!");
    }
}
