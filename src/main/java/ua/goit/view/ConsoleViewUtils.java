package ua.goit.view;

import ua.goit.model.entity.TableNames;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleViewUtils implements Closeable{

    static final int CREATE  = 1;
    static final int READ    = 2;
    static final int UPDATE  = 3;
    static final int DELETE  = 4;
    static final int SHOWALL = 5;
    static final int ADD     = 6;

    static final int EXIT_SYSTEM             = 0;
    public static final int COMPANIES_TABLE  = 1;
    public static final int CUSTOMERS_TABLE  = 2;
    public static final int DEVELOPERS_TABLE = 3;
    public static final int PROJECTS_TABLE   = 4;
    public static final int SKILLS_TABLE     = 5;

    private ConsoleViewUtils() {
    }

    @Override
    public void close() throws IOException {
        if(bufferedReader != null) {
            bufferedReader.close();
        }
    }

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int readInt() throws IOException {
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

    static Long readLong() throws IOException {
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

    static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    static void writeMessage(String message) {
        System.out.println(message);
    }
}
