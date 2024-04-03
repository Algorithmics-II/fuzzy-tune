package org.example.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.ElementType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
  private int id;
  private String name;
  private int age;
  private List<String> favorite_genres;
  private List<String> favorite_artists;
  private List<String> favorite_songs;
  private List<String> recently_played;


  public User() {
  }

  public User(int id, String name, int age, List<String> favorite_genres,
              List<String> favorite_artists, List<String> favorite_songs,
              List<String> recently_played) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.favorite_genres = favorite_genres;
    this.favorite_artists = favorite_artists;
    this.favorite_songs = favorite_songs;
    this.recently_played = recently_played;
  }

  /**
   * This method converts the User class into a Document to be able to make Match.
   * @return Document converted
   */
  public Document toDocument() {
    Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

    documentBuilder.addElement(new Element.Builder().setType(ElementType.NAME).setValue(this.name).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.AGE).setValue(this.age).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favorite_genres)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favorite_artists)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favorite_songs)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.recently_played)).createElement());

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
    return this.favorite_genres;
  }

  public List<String> getFavoriteArtists() {
    return this.favorite_artists;
  }

  public List<String> getFavorite_songs() {
    return this.favorite_songs;
  }

  public List<String> getRecently_played() {
    return this.recently_played;
  }
}
