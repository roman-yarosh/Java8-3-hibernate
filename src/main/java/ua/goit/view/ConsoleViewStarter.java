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
        int userChoise;

        showSelectTables();
        userChoise = readInt();

        if (userChoise == 0) {
            writeMessage("Exit Project Management System!");
            System.exit(0);
        }
        if (userChoise == TableNames.Companies.getTableNum()) {
            companyView.companyViewStarter();
        }
        if (userChoise == TableNames.Customers.getTableNum()) {
            customerView.customerViewStarter();
        }
        if (userChoise == TableNames.Developers.getTableNum()) {
            developerView.developerViewStarter();
        }
        if (userChoise == TableNames.Projects.getTableNum()) {
            projectView.projectViewStarter();
        }
        if (userChoise == TableNames.Skills.getTableNum()) {
            skillView.skillViewStarter();
        }
        if (userChoise > TableNames.Skills.getTableNum()) {
            writeMessage("Wrong input! Try one more time!");
            startApp();
        }
    }
}
