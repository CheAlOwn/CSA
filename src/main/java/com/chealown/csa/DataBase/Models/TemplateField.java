package com.chealown.csa.DataBase.Models;

public class TemplateField {
    private int id;
    private String placeholder; // Идентификатор ввода в документе
    private String label; // Название идентификатора поля для формы редактирования
    private boolean required; // Необходимое для заполнения поле
    private String defaultValue;
    private String options;

    public TemplateField() {}

    public TemplateField(String placeholder, String label) {
        this.placeholder = placeholder;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
