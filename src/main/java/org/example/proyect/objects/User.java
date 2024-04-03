package org.example.proyect.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.ElementType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String name;
    private int age;
    @JsonProperty("favorite_genres")
    private List<String> favoriteGenres;
    @JsonProperty("favorite_artists")
    private List<String> favoriteArtists;
    @JsonProperty("favorite_songs")
    private List<String> favoriteSongs;
    @JsonProperty("recently_played")
    private List<String> recentlyPlayed;
    private Document document;
    private Document documentPreferences;

    public User(){

    }
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
        this.document = toDocument();
        this.documentPreferences = toDocumentPreferences();
    }

    /**
     * This method converts the User class into a Document to be able to make Match.
     *
     * @return Document converted
     */
    private Document toDocument() {
        Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

        documentBuilder.addElement(new Element.Builder().setType(ElementType.AGE).setValue(this.age).createElement());
        documentBuilder.addElement(new Element.Builder().setType(ElementType.ARRAY).setValue(this.favoriteGenres).createElement());
        documentBuilder.addElement(new Element.Builder().setType(ElementType.ARRAY).setValue(this.favoriteArtists).createElement());
        documentBuilder.addElement(new Element.Builder().setType(ElementType.ARRAY).setValue(this.favoriteSongs).createElement());
        documentBuilder.addElement(new Element.Builder().setType(ElementType.ARRAY).setValue(this.recentlyPlayed).createElement());

        return documentBuilder.createDocument();
    }

    /**
     * This method converts the User class into a Document to be able to make Match.
     * @return Document converted
     */
    private Document toDocumentPreferences() {
        Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

        documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favoriteGenres)).createElement());
        documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.join(" ", this.favoriteArtists)).createElement());

        return documentBuilder.createDocument();
    }

    public void createDocument() {
        this.document = toDocument();
    }

    public void creatDocumentPreferences() {
        this.documentPreferences = toDocumentPreferences();
    }

    public Document getDocument() {
        return this.document;
    }

    public Document getDocumentPreferences() {
        return this.documentPreferences;
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
