package ua.goit.view;


import ua.goit.model.entity.DeveloperFields;

import java.util.Scanner;

public class ConsoleView {

    public static void showCrudDialod() {
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
                scannEntity(inputEntity, inputCRUD);


                System.out.println("FieldNames: " + DeveloperFields.valueOf("DEVELOPER_ID").getFieldName());

            } while (inputCRUD > 0);
        } while (inputEntity > 0);
    }

    private static void scannEntity(int inputEntity, int inputCRUD) {

    }
}
