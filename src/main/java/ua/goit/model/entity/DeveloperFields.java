package ua.goit.model.entity;

public enum DeveloperFields {

    DEVELOPER_ID("ID разработчика"),
    NAME("ФИО разработчика"),
    EXPERIENCE("Опыт работы"),
    SALARY("Зарплата");

    private String fieldName;

    DeveloperFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
