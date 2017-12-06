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

        System.out.print("Select entity for CRUD operations: 0 - Exit");
        for (TableNames tableName : TableNames.values()) {
            System.out.print(String.format(", %s - %s", tableName.getTableNum(), tableName));
        }
        writeMessage(": ");

        userChoice = readInt();

        switch (userChoice) {
            case EXIT_SYSTEM:
                writeMessage("Exit Project Management System!");
                System.exit(0);
                break;
            case COMPANIES_TABLE:
                writeMessage(String.format("Selected entity %s!", TableNames.Companies.getTableName()));
                companyView.companyViewStarter();
                break;
            case CUSTOMERS_TABLE:
                writeMessage(String.format("Selected entity %s!", TableNames.Customers.getTableName()));
                customerView.customerViewStarter();
                break;
            case DEVELOPERS_TABLE:
                writeMessage(String.format("Selected entity %s!", TableNames.Developers.getTableName()));
                developerView.developerViewStarter();
                break;
            case PROJECTS_TABLE:
                writeMessage(String.format("Selected entity %s!", TableNames.Projects.getTableName()));
                projectView.projectViewStarter();
                break;
            case SKILLS_TABLE:
                writeMessage(String.format("Selected entity %s!", TableNames.Skills.getTableName()));
                skillView.skillViewStarter();
                break;
            default:
                writeMessage("Wrong input! Try one more time!");
                startApp();
        }
    }
}
