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

  public User(int id, String name, int age, List<String> favoriteGenres,
              List<String> favoriteArtists, List<String> favoriteSongs,
              List<String> recentlyPlayed) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.favoriteGenres = favoriteGenres;
    this.favoriteArtists = favoriteArtists;
    this.favoriteSongs = favoriteSongs;
    this.recentlyPlayed = recentlyPlayed;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public List<String> getFavoriteGenres() {
    return this.favoriteGenres;
  }

  public List<String> getFavoriteArtists() {
    return this.favoriteArtists;
  }

  public List<String> getFavoriteSongs() {
    return this.favoriteSongs;
  }

  public List<String> getRecentlyPlayed() {
    return this.recentlyPlayed;
  }
}
