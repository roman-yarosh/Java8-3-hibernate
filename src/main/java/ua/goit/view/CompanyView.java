package ua.goit.view;

import ua.goit.controller.CompanyController;
import ua.goit.model.entity.Company;
import ua.goit.model.entity.CompanyFields;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class CompanyView {

    public void companyViewStarter() throws IOException {

        Company company = new Company();
        CompanyController companyController = new CompanyController();
        Long companyId;
        String companyName;
        String companyAddress;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, any other - Exit to main menu,:");
        int userChoise = readInt();
        switch (userChoise) {
            case 1:
                writeMessage("Enter new " + CompanyFields.COMPANY_NAME.getFieldName() + ":");
                companyName = readString();
                writeMessage("Enter new " + CompanyFields.COMPANY_ADDRESS.getFieldName() + ":");
                companyAddress = readString();

                company.setCompanyName(companyName);
                company.setCompanyAddress(companyAddress);

                companyController.create(company);
                writeMessage("Success!");
                break;
            case 2:
                writeMessage("Enter " + CompanyFields.COMPANY_ID.getFieldName() + ":");
                companyId = readLong();

                writeMessage(companyController.read(companyId).toString());
                writeMessage("Success!");
                break;
            case 3:
                writeMessage("Enter " + CompanyFields.COMPANY_ID.getFieldName() + " for update:");
                companyId = readLong();
                writeMessage("Enter new " + CompanyFields.COMPANY_NAME.getFieldName() + ":");
                companyName = readString();
                writeMessage("Enter new " + CompanyFields.COMPANY_ADDRESS.getFieldName() + ":");
                companyAddress = readString();

                company.setCompanyId(companyId);
                company.setCompanyName(companyName);
                company.setCompanyAddress(companyAddress);

                companyController.update(company);
                writeMessage("Success!");
                break;
            case 4:
                writeMessage("Enter " + CompanyFields.COMPANY_ID.getFieldName() + " for delete:");
                companyId = readLong();

                company.setCompanyId(companyId);

                companyController.delete(company);
                writeMessage("Success!");
                break;
            case 5:
                writeMessage("Companies list:");
                companyController.getAll().forEach(System.out::println);
                writeMessage("Success!");
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
