package com.example.rockpedia.band;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(subTypes = Band.class)
public class BandTemplate {
    @ApiModelProperty(value = "name", example = "S.U.T.U.R.E.")
    public String name;
    @ApiModelProperty(value = "genre", example = "Black/Crust")
    public String genre;
    @ApiModelProperty(value = "themes", example = "Misanthropy")
    public String themes;
    @ApiModelProperty(value = "location", example = "Black Pandemie Production")
    public String location;
    @ApiModelProperty(value = "country", example = "France")
    public String country;
    @ApiModelProperty(value = "label", example = "Metz, Grand Est")
    public String label;
    @ApiModelProperty(value = "status", example = "Active")
    public String status;
    @ApiModelProperty(value = "formed", example = "2016")
    public int formed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFormed() {
        return formed;
    }

    public void setFormed(int formed) {
        this.formed = formed;
    }
}
