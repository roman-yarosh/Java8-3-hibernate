package ua.goit.view;

import ua.goit.controller.jdbc.CompanyController;
import ua.goit.model.entity.*;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class CompanyView {

    public void companyViewStarter() throws IOException {

        Company company = new Company();
        CompanyController companyController = new CompanyController();

        Long companyId;
        String companyName;
        String companyAddress;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, 6 - Add customers, any other - Exit to main menu:");
        int userChoice = readInt();
        switch (userChoice) {
            case CREATE:
                writeMessage(String.format("Enter new %s:", CompanyFields.COMPANY_NAME.getFieldName()));
                companyName = readString();
                writeMessage(String.format("Enter new %s:", CompanyFields.COMPANY_ADDRESS.getFieldName()));
                companyAddress = readString();

                company.setCompanyName(companyName);
                company.setCompanyAddress(companyAddress);

                companyController.create(company);
                writeMessage("Success!");
                break;
            case READ:
                writeMessage(String.format("Enter %s:", CompanyFields.COMPANY_ID.getFieldName()));
                companyId = readLong();

                writeMessage(companyController.read(companyId).toString());
                writeMessage("Success!");
                break;
            case UPDATE:
                writeMessage(String.format("Enter %s for update:", CompanyFields.COMPANY_ID.getFieldName()));
                companyId = readLong();
                writeMessage(String.format("Enter new %s:", CompanyFields.COMPANY_NAME.getFieldName()));
                companyName = readString();
                writeMessage(String.format("Enter new %s:", CompanyFields.COMPANY_ADDRESS.getFieldName()));
                companyAddress = readString();

                company.setCompanyId(companyId);
                company.setCompanyName(companyName);
                company.setCompanyAddress(companyAddress);

                companyController.update(company);
                writeMessage("Success!");
                break;
            case DELETE:
                writeMessage(String.format("Enter %s for delete:", CompanyFields.COMPANY_ID.getFieldName()));
                companyId = readLong();

                company.setCompanyId(companyId);

                companyController.delete(company);
                writeMessage("Success!");
                break;
            case SHOWALL:
                writeMessage("Companies list:");
                companyController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case ADD:
                writeMessage(String.format("Enter %s:", CompanyFields.COMPANY_ID.getFieldName()));
                companyId = readLong();
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_NAME.getFieldName()));
                companyName = readString();
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_ADDRESS.getFieldName()));
                companyAddress = readString();

                companyController.createCompanyCustomer(companyId, companyName, companyAddress);
                break;
            default:
                writeMessage("Exit to main menu!");
                ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
                consoleViewStarter.startApp();
                break;
        }
        companyViewStarter();
    }
}
