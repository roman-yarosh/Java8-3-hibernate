package ua.goit.model.entity;

public enum CompanyFields {

    COMPANY_ID("ID компании"),
    COMPANY_NAME("Название компании"),
    COMPANY_ADDRESS("Адрес компании");

    private String fieldName;

    CompanyFields(String field){
        this.fieldName = field;
    }

    public String getFieldName() {
        return fieldName;
    }
}
