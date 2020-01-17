package com.example.rockpedia.band;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Band")
public
class Band {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 128, nullable = false)
    private String name;
    @Column(name = "genre", length = 128, nullable = false)
    private String genre;
    @Column(name = "themes", nullable = false)
    private String themes;
    @Column(name = "location", length = 128, nullable = false)
    private String location;
    @Column(name = "country", length = 128, nullable = false)
    private String country;
    @Column(name = "label", length = 128, nullable = false)
    private String label;
    @Column(name = "status", length = 128, nullable = false)
    private String status;
    @Column(name = "formed", length = 128, nullable = false)
    private int formed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        JsonObject json = Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("genre", genre)
                .add("themes", themes)
                .add("location", location)
                .add("country", country)
                .add("label", label)
                .add("status", status)
                .add("formed", formed)
                .build();
        return json.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return formed == band.formed &&
                id.equals(band.id) &&
                name.equals(band.name) &&
                genre.equals(band.genre) &&
                themes.equals(band.themes) &&
                location.equals(band.location) &&
                country.equals(band.country) &&
                label.equals(band.label) &&
                status.equals(band.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, themes, location, country, label, status, formed);
    }

    public String valueIsNull()
    {
        if(name == null)
            return "name";
        else if (genre == null)
            return "genre";
        else if (themes == null)
            return "themes";
        else if (location == null)
            return "location";
        else if (country == null)
            return "country";
        else if (label == null)
            return "label";
        else if (status == null)
            return "status";
        return null;
    }
}
