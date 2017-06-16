package ua.goit.model.entity;

public enum ProjectFields {

    PROJECT_ID("ID проекта"),
    PROJECT_NAME("Название проекта"),
    COST("Стоимость проекта");

    private String fieldName;

    private ProjectFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
