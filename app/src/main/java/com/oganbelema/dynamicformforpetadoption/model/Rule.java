
package com.oganbelema.dynamicformforpetadoption.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rule {

    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("otherwise")
    @Expose
    private String otherwise;
    @SerializedName("targets")
    @Expose
    private List<String> targets = null;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOtherwise() {
        return otherwise;
    }

    public void setOtherwise(String otherwise) {
        this.otherwise = otherwise;
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

}
