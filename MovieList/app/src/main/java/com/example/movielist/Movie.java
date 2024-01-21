package com.example.movielist; // Замените 'com.example.movielist' на вашу актуальную структуру пакета

public class Movie {
    private String title;
    private String director;
    private int year;
    private boolean favorite;

    public Movie(String title, String director, int year, boolean favorite) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
