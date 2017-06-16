package ua.goit;

import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.dao.JdbcDeveloperDAO;
import ua.goit.model.entity.DeveloperFields;

import java.util.Scanner;

import static ua.goit.view.ConsoleView.showCrudDialod;

public class Main {

    public static void main(String[] args) {

        DeveloperDAO developerDAO = new JdbcDeveloperDAO();
        System.out.println(developerDAO.read(2));
        developerDAO.getAll().forEach(System.out::println);

//        System.exit(0);

        showCrudDialod();
    }
}
