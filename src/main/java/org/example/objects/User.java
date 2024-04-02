package org.example.objects;

import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.ElementType;

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

  /**
   * This method converts the User class into a Document to be able to make Match.
   * @return Document converted
   */
  public Document toDocument() {
    Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

    documentBuilder.addElement(new Element.Builder().setType(ElementType.NAME).setValue(this.name).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.AGE).setValue(this.age).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favoriteGenres)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favoriteArtists)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favoriteSongs)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.recentlyPlayed)).createElement());

    return documentBuilder.createDocument();
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
