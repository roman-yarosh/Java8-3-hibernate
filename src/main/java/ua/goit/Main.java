package ua.goit;

import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.dao.JdbcDeveloperDAO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DeveloperDAO developerDAO = new JdbcDeveloperDAO();
        System.out.println(developerDAO.read(2));
        developerDAO.getAll().forEach(System.out::println);

        Scanner in = new Scanner(System.in);
        int inputEntity;
        int inputCRUD;
        do {
            System.out.println("Select entity: 1 - Developer, 0 - Exit:");
            inputEntity = in.nextInt();
            //правильную обработку ввода если не целое
            System.out.println("Selected entity: " + inputEntity);
            if (inputEntity > 0) do {
                System.out.println("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 0 - Exit:");
                inputCRUD = in.nextInt();
                System.out.println("You selected entity: " + inputEntity + " and operation: " + inputCRUD);
                //scannDeveloper();
                System.out.println("You selected entity: " + inputEntity + " and operation: " + inputCRUD);


            } while (inputCRUD > 0);
        } while (inputEntity > 0);
    }
}
