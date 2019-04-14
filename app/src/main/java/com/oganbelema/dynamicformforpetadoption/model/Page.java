
package com.oganbelema.dynamicformforpetadoption.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Page {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
