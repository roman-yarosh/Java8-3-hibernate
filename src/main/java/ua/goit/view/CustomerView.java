package ua.goit.view;

import ua.goit.controller.jdbc.CustomerController;
import ua.goit.model.entity.Customer;
import ua.goit.model.entity.CustomerFields;
import ua.goit.model.entity.ProjectFields;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class CustomerView {

    public void customerViewStarter() throws IOException {

        Customer customer = new Customer();
        CustomerController customerController = new CustomerController();

        Long customerId;
        String customerName;
        String customerAddress;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, 6 - Add projects, any other - Exit to main menu:");
        int userChoice = readInt();
        switch (userChoice) {
            case CREATE:
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_NAME.getFieldName()));
                customerName = readString();
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_ADDRESS.getFieldName()));
                customerAddress = readString();

                customer.setCustomerName(customerName);
                customer.setCustomerAddress(customerAddress);

                customerController.create(customer);
                writeMessage("Success!");
                break;
            case READ:
                writeMessage(String.format("Enter %s:", CustomerFields.CUSTOMER_ID.getFieldName()));
                customerId = readLong();

                writeMessage(customerController.read(customerId).toString());
                writeMessage("Success!");
                break;
            case UPDATE:
                writeMessage(String.format("Enter %s for update:", CustomerFields.CUSTOMER_ID.getFieldName()));
                customerId = readLong();
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_NAME.getFieldName()));
                customerName = readString();
                writeMessage(String.format("Enter new %s:", CustomerFields.CUSTOMER_ADDRESS.getFieldName()));
                customerAddress = readString();

                customer.setCustomerId(customerId);
                customer.setCustomerName(customerName);
                customer.setCustomerAddress(customerAddress);

                customerController.update(customer);
                writeMessage("Success!");
                break;
            case DELETE:
                writeMessage(String.format("Enter %s for delete:", CustomerFields.CUSTOMER_ID.getFieldName()));
                customerId = readLong();

                customer.setCustomerId(customerId);

                customerController.delete(customer);
                writeMessage("Success!");
                break;
            case SHOWALL:
                writeMessage("Customers list:");
                customerController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            case ADD:
                writeMessage(String.format("Enter %s:", CustomerFields.CUSTOMER_ID.getFieldName()));
                customerId = readLong();
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_NAME.getFieldName()));
                String projectName = readString();
                writeMessage(String.format("Enter new %s:", ProjectFields.PROJECT_COST.getFieldName()));
                int projectCost = readInt();

                customerController.createCustomerProject(customerId, projectName, projectCost);
                break;
            default:
                writeMessage("Exit to main menu!");
                ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
                consoleViewStarter.startApp();
                break;
        }
        customerViewStarter();
    }
}
