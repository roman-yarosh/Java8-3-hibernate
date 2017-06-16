package ua.goit;

import ua.goit.model.dao.DeveloperDAO;
import ua.goit.model.dao.JdbcDeveloperDAO;

import static ua.goit.view.ConsoleView.showCrudDialod;

public class Main {

    public static void main(String[] args) {

        DeveloperDAO developerDAO = new JdbcDeveloperDAO();
        //System.out.println(developerDAO.read(Long.valueOf(1)));
        developerDAO.getAllDevelopers().forEach(System.out::println);



//        System.exit(0);

        showCrudDialod();
    }


}
