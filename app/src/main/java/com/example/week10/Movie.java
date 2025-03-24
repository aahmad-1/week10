package com.example.week10;

public class Movie {
    private String title, genre, posterResource;
    private Integer year;

    public Movie(String title, Integer year, String genre, String posterResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    public String getTitle() {
        return (title == null || title.equals("null")) ? "Unknown Title" : title;
    }

    public String getYear() {
        return (year == null) ? "Unknown year" : String.valueOf(year);
    }

    public String getGenre() {
        return (genre == null || genre.equals("null")) ? "Unknown Genre" : genre;
    }

    public String getPosterResource() {
        return (posterResource == null || posterResource.equals("null")) ? "default_poster" : posterResource;
    }
}