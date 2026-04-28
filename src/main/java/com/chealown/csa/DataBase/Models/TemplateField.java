package com.chealown.csa.DataBase.Models;

public class TemplateField {
    private int id = -1;
    private int templateId;
    private String placeholder;
    private String label;
    private boolean required;
    private String defaultValue;

    public TemplateField() {}

    public TemplateField(int id, int templateId, String placeholder, String label, boolean required, String defaultValue) {
        this.id = id;
        this.templateId = templateId;
        this.placeholder = placeholder;
        this.label = label;
        this.required = required;
        this.defaultValue = defaultValue;
    }

    public TemplateField(String placeholder, String label) {
        this.placeholder = placeholder;
        this.label = label;
        this.required = false; // По умолчанию false
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
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

    public boolean isRequired() {  // Метод должен называться isRequired для JavaBean convention
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
}