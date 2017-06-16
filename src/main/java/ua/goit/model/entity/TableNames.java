package ua.goit.model.entity;

public enum TableNames {

    Developers(1),
    Companies(2),
    Customers(3),
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
            case 1 : return "Developers";
            case 2 : return "Companies";
            case 3 : return "Customers";
            case 4 : return "Projects";
            case 5 : return "Skills";
            default: return "";
        }
    }
}
