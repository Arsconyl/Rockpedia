package com.example.rockpedia.band;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Band")
public class Band {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 128, nullable = false)
    private String name;
    @Column(name = "genre", length = 128, nullable = false)
    private String genre;
    @Column(name = "themes", nullable = false)
    public String themes;
    @Column(name = "location", length = 128, nullable = false)
    private String location;
    @Column(name = "country", length = 128, nullable = false)
    private String country;
    @Column(name = "label", length = 128, nullable = false)
    public String label;
    @Column(name = "status", length = 128, nullable = false)
    public String status;
    @Column(name = "formed", length = 128, nullable = false)
    public int formed;

    public Band() {
    }

    public Band(String name, String genre, String themes, String location, String country, String label, String status, int formed) {
        this.name = name;
        this.genre = genre;
        this.themes = themes;
        this.location = location;
        this.country = country;
        this.label = label;
        this.status = status;
        this.formed = formed;
    }

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

    public int getformed() {
        return formed;
    }

    public void setformed(int formed) {
        this.formed = formed;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", themes='" + themes + '\'' +
                ", location='" + location + '\'' +
                ", country='" + country + '\'' +
                ", label='" + label + '\'' +
                ", status='" + status + '\'' +
                ", formed=" + formed +
                '}';
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
}
