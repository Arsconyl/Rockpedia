package com.example.rockpedia.entity;

import java.util.ArrayList;

public class Band {
    private int id;
    private String name;
    private String members;
    private String style;
    private int yearofcreation;
    private String townoforigin;

    public int getId() {
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

    private String label;

    public Band(int id, String name, String members, String style, int yearofcreation, String townoforigin, String label) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.style = style;
        this.yearofcreation = yearofcreation;
        this.townoforigin = townoforigin;
        this.label = label;
    }
}
