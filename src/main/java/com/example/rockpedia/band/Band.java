package com.example.rockpedia.band;

import net.minidev.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name="BAND")
public class Band {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 64, nullable = false)
    private String name;
    @Column(name = "Members", length = 128, nullable = false)
    private String members;
    @Column(name = "Style", length = 64, nullable = false)
    private String style;
    @Column(name = "Year_creation", nullable = false)
    private int yearofcreation;
    @Column(name = "townoforigin", length = 64)
    private String townoforigin;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMembers() {
        return members;
    }

    public String getStyle() {
        return style;
    }

    public int getYearofcreation() {
        return yearofcreation;
    }

    public String getTownoforigin() {
        return townoforigin;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setYearofcreation(int yearofcreation) {
        this.yearofcreation = yearofcreation;
    }

    public void setTownoforigin(String townoforigin) {
        this.townoforigin = townoforigin;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members='" + members + '\'' +
                ", style='" + style + '\'' +
                ", yearofcreation=" + yearofcreation +
                ", townoforigin='" + townoforigin + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
    
    public JSONObject toJSON()
    {
        JSONObject entity = new JSONObject();
        entity.put("id", this.getId());
        entity.put("Name", this.getName());
        entity.put("members", this.getMembers());
        entity.put("style", this.getStyle());
        entity.put("members", this.getMembers());
        entity.put("yearofcreation", this.getYearofcreation());
        entity.put("townoforigin", this.getTownoforigin());
        entity.put("label", this.getLabel());
        return entity;
    }
}
