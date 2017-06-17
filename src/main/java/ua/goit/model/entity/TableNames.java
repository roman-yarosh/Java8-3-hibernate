package ua.goit.model.entity;

public enum TableNames {

    Companies(1),
    Customers(2),
    Developers(3),
    Projects(4),
    Skills(5);

    private int tableNum;

    TableNames(int num){
        this.tableNum = num;
    }

    public int getTableNum() {
        return tableNum;
    }

    public String getAnyTableNameByNum(int tableNumber){
        switch (tableNumber) {
            case 1 : return "Companies";
            case 2 : return "Customers";
            case 3 : return "Developers";
            case 4 : return "Projects";
            case 5 : return "Skills";
            default: return "";
        }
    }
}
