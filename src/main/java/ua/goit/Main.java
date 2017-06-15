package ua.goit;

import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.dao.JdbcDeveloperDAO;

public class Main {

    public static void main(String[] args) {

        DeveloperDAO developerDAO = new JdbcDeveloperDAO();
        System.out.println(developerDAO.read(2));
        developerDAO.getAll().forEach(System.out::println);

    }
}
