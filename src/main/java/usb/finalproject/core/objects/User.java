package usb.finalproject.core.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import usb.finalproject.fuzzy.matcher.domain.Document;
import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.ElementType;

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
    private Document documentRecentlyPlayed;

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
        this.documentRecentlyPlayed = toDocumentRecentlyPlayed();
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

    /**
     * This method converts the User class into a Document to be able to make Match.
     * @return Document converted
     */
    private Document toDocumentRecentlyPlayed() {
        Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

        // Iterate through the list of recently played music tracks.
        for (var music: recentlyPlayed) {
            // Add each music track as an element of type NAMETRACK to the Document Builder.
            documentBuilder.addElement(new Element.Builder().setType(ElementType.NAMETRACK).setValue(music).createElement());
        }

        return documentBuilder.createDocument();
    }

    /**
     * This method will create a document for User
     */
    public void createDocument() {
        this.document = toDocument();
    }

    /**
     * This method will create a document for a preferences User
     */
    public void creatDocumentPreferences() {
        this.documentPreferences = toDocumentPreferences();
    }

    /**
     * This method will create a Document for the recently played music User
     */
    public void createDocumentRecentlyPlayed() {
        this.documentRecentlyPlayed = toDocumentRecentlyPlayed();
    }

    /**
     * This method will return the Document created verifying if this is not null
     * @return document created
     */
    public Document getDocument() {
        if (this.document == null)
            this.document = toDocument();
        return this.document;
    }

    /**
     * This method will return the document created for preferences User verifying if this is not null
     * @return document created
     */
    public Document getDocumentPreferences() {
        if (this.documentPreferences == null)
            this.documentPreferences = toDocumentPreferences();
        return this.documentPreferences;
    }
    
    /**
     * This method will return the Document created for the recently played music User verifying if this is not null
     * @return document created
     */
    public Document getDocumentRecentlyPlayed() {
        if (this.documentRecentlyPlayed == null)
            this.documentRecentlyPlayed = toDocumentRecentlyPlayed();
        return this.documentRecentlyPlayed;
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

    @Override
    public String toString() {
        String userInformation =
                "User:\n" +
                        " Name: " + name + "\n" +
                        " Age: " + age + "\n" +
                        " Favorite Genres: " + (favoriteGenres != null ? favoriteGenres : "N/A") + "\n" +
                        " Favorite Artists: " + (favoriteArtists != null ? favoriteArtists : "N/A") + "\n" +
                        " Favorite Songs: " + (favoriteSongs != null ? favoriteSongs : "N/A") + "\n" +
                        " Recently Played: " + (recentlyPlayed != null ? recentlyPlayed : "N/A");

        return userInformation;
    }
}
