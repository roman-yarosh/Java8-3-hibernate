package ua.goit.model.entity;

public enum SkillFields {

    SKILL_ID("ID навыка"),
    SKILL_NAME("Название навыка");

    private String fieldName;

    SkillFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
