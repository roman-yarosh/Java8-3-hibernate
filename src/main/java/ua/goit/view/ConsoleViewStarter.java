package ua.goit.view;

import ua.goit.model.entity.TableNames;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class ConsoleViewStarter {

    private CompanyView companyView;
    private CustomerView customerView;
    private DeveloperView developerView;
    private ProjectView projectView;
    private SkillView skillView;

    public ConsoleViewStarter() {
        this.companyView = new CompanyView();
        this.customerView = new CustomerView();
        this.developerView = new DeveloperView();
        this.projectView = new ProjectView();
        this.skillView = new SkillView();
    }

    public void startApp() throws IOException {
        int userChoice;

        showSelectTables();
        userChoice = readInt();

        if (userChoice == 0) {
            writeMessage("Exit Project Management System!");
            System.exit(0);
        }
        writeMessage("Selected entity " + TableNames.Companies.getAnyTableNameByNum(userChoice) + "!");
        if (userChoice == TableNames.Companies.getTableNum()) {
            companyView.companyViewStarter();
        }
        if (userChoice == TableNames.Customers.getTableNum()) {
            customerView.customerViewStarter();
        }
        if (userChoice == TableNames.Developers.getTableNum()) {
            developerView.developerViewStarter();
        }
        if (userChoice == TableNames.Projects.getTableNum()) {
            projectView.projectViewStarter();
        }
        if (userChoice == TableNames.Skills.getTableNum()) {
            skillView.skillViewStarter();
        }
        if (userChoice > TableNames.Skills.getTableNum()) {
            writeMessage("Wrong input! Try one more time!");
            startApp();
        }
    }
}
