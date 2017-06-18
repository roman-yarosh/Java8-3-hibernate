package ua.goit.view;

import ua.goit.model.entity.TableNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleViewUtils {

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt() throws IOException {
        int input = -1;
        do {
            try {
                input = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException e) {
                writeMessage(String.format("Wrong input! Please enter correct integer value! %s!", e.getMessage()));
            }
        } while (input == -1);
        return input;
    }

    public static Long readLong() throws IOException {
        Long input = null;
        do {
            try {
                input = Long.parseLong(bufferedReader.readLine());
            } catch (NumberFormatException e) {
                writeMessage(String.format("Wrong input! Please enter correct Long value! %s!", e.getMessage()));
            }
        } while (input == null);
        return input;
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void showSelectTables() {
        System.out.print("Select entity for CRUD operations: 0 - Exit");
        for (TableNames tableName : TableNames.values()) {
            System.out.print(String.format(", %s - %s", tableName.getTableNum(), tableName));
        }
        System.out.println(": ");
    }
}
