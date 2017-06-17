package ua.goit;

import ua.goit.view.ConsoleViewStarter;

import java.io.IOException;

import static ua.goit.view.ConsoleViewStarter.showCrudDialod;

public class PMSystemStarter {

    public static void main(String[] args) throws IOException {

        ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
        consoleViewStarter.startApp();


        //DeveloperDAO developerDAO = new JdbcDeveloperDAO();
        //System.out.println(developerDAO.read(Long.valueOf(1)));
        //developerDAO.getAllDevelopers().forEach(System.out::println);
        //showCrudDialod();
        //System.exit(0);
    }


}
