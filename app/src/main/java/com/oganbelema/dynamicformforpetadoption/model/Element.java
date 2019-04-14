
package com.oganbelema.dynamicformforpetadoption.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Element {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("rules")
    @Expose
    private List<Rule> rules = null;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("isMandatory")
    @Expose
    private Boolean isMandatory;
    @SerializedName("keyboard")
    @Expose
    private String keyboard;
    @SerializedName("formattedNumeric")
    @Expose
    private String formattedNumeric;
    @SerializedName("mode")
    @Expose
    private String mode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getFormattedNumeric() {
        return formattedNumeric;
    }

    public void setFormattedNumeric(String formattedNumeric) {
        this.formattedNumeric = formattedNumeric;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
