package ua.goit.view;

import ua.goit.model.entity.TableNames;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleViewUtils implements Closeable{

    private ConsoleViewUtils() {
    }

    @Override
    public void close() throws IOException {
        if(bufferedReader != null) {
            bufferedReader.close();
        }
    }

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    protected static final int CREATE  = 1;
    protected static final int READ    = 2;
    protected static final int UPDATE  = 3;
    protected static final int DELETE  = 4;
    protected static final int SHOWALL = 5;
    protected static final int ADD     = 6;

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
}
