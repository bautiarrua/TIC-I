package org.example.tici.DTO;

public class MovieDTO {
    private String title;
    private String imageUrl;
    private String description;
    private String genere;
    private int duration;

    public MovieDTO(String title,String imageUrl,
                    String genere,String description, int duration) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.genere = genere;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
