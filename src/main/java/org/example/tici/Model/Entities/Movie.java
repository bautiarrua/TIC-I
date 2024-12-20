package org.example.tici.Model.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @Column(length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 10)
    private String age;
    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false, length = 50)
    private String language;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = true, length = 50)
    private String type;

    @Column(nullable = true, length = 700)
    private String imageUrl;

    @Column(nullable = true, length = 700)
    private String trilerUrl;

    @ManyToMany(mappedBy = "movies")
    private List<Billboard> billboards;

    public Movie() {
    }

    public Movie(String title, String description, String genre, String language, int duration, String type, String imageUrl, String trilerUrl, String age) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.type = type;
        this.imageUrl = imageUrl;
        this.trilerUrl = trilerUrl;
        this.age= age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrilerUrl() {
        return trilerUrl;
    }

    public void setTrilerUrl(String trilerUrl) {
        this.trilerUrl = trilerUrl;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}


