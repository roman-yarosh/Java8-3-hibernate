package ua.goit.model.entity;

import static ua.goit.view.ConsoleViewUtils.*;

public enum TableNames {

    Companies("Companies", COMPANIES_TABLE),
    Customers("Customers", CUSTOMERS_TABLE),
    Developers("Developers", DEVELOPERS_TABLE),
    Projects("Projects", PROJECTS_TABLE),
    Skills("Skills", SKILLS_TABLE);

    private String tableName;
    private int tableNum;

    TableNames(String tableName, int tableNum) {
        this.tableName = tableName;
        this.tableNum = tableNum;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}
