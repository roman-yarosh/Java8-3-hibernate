package ua.goit.model.entity;

public enum ProjectFields {

    PROJECT_ID("ID проекта"),
    PROJECT_NAME("Название проекта"),
    PROJECT_COST("Стоимость проекта");

    private String fieldName;

    ProjectFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
