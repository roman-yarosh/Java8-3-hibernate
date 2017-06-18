package ua.goit.model.entity;

public enum CustomerFields {

    CUSTOMER_ID("ID заказчика"),
    CUSTOMER_NAME("Название заказчика"),
    CUSTOMER_ADDRESS("Адрес заказчика");

    private String fieldName;

    CustomerFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
