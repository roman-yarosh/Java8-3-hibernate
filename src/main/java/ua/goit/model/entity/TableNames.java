package ua.goit.model.entity;

public enum TableNames {

    Companies(1),
    Customers(2),
    Developers(3),
    Projects(4),
    Skills(5);

    private int fieldName;

    private TableNames(int field){
        this.fieldName = field;
    }

    public int getTableNum() {
        return fieldName;
    }
}
