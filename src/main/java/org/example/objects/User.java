package org.example.objects;

import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<String> favoriteGenres;
    private List<String> favoriteArtists;
    private List<String> favoriteSongs;
    private List<String> recentlyPlayed;

    public User(int id, String name, int age, List<String> favoriteGenres, List<String> favoriteArtists,
                   List<String> favoriteSongs, List<String> recentlyPlayed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteGenres = favoriteGenres;
        this.favoriteArtists = favoriteArtists;
        this.favoriteSongs = favoriteSongs;
        this.recentlyPlayed = recentlyPlayed;
    }
}
