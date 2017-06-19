package ua.goit.model.entity;

public enum TableNames {

    Companies(1),
    Customers(2),
    Developers(3),
    Projects(4),
    Skills(5);

    private int tableNum;

    TableNames(int tableNum) {
        this.tableNum = tableNum;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}
