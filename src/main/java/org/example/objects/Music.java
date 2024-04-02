package org.example.objects;

import org.example.fuzzy_matcher.domain.Document;
import org.example.fuzzy_matcher.domain.Element;
import org.example.fuzzy_matcher.domain.ElementType;

import java.util.List;

public class Music {
  private int id;
  private String trackName;
  private String artistNames;
  private String albumName;
  private String albumArtistNames;
  private String albumReleaseDate;
  private String albumImageUrl;
  private String artistGenres;
  private int discNumber;
  private int trackNumber;
  private long trackDurationMs;
  private boolean explicit;
  private int popularity;
  private double danceability;
  private double energy;
  private double speechiness;
  private double acousticness;
  private double instrumentalness;
  private double liveness;
  private double valence;
  private String trackPreviewUrl;

  public Music(int id, String trackName, String artistNames, String albumName,
               String albumArtistNames, String albumReleaseDate, String albumImageUrl,
               String artistGenres, int discNumber, int trackNumber, long trackDurationMs,
               boolean explicit, int popularity, double danceability, double energy,
               double speechiness, double acousticness, double instrumentalness,
               double liveness, double valence, String trackPreviewUrl) {
    this.id = id;
    this.trackName = trackName;
    this.artistNames = artistNames;
    this.albumName = albumName;
    this.albumArtistNames = albumArtistNames;
    this.albumReleaseDate = albumReleaseDate;
    this.albumImageUrl = albumImageUrl;
    this.artistGenres = artistGenres;
    this.discNumber = discNumber;
    this.trackNumber = trackNumber;
    this.trackDurationMs = trackDurationMs;
    this.explicit = explicit;
    this.popularity = popularity;
    this.danceability = danceability;
    this.energy = energy;
    this.speechiness = speechiness;
    this.acousticness = acousticness;
    this.instrumentalness = instrumentalness;
    this.liveness = liveness;
    this.valence = valence;
    this.trackPreviewUrl = trackPreviewUrl;
  }

  /**
   * This method converts the Music class into a Document to be able to make Match.
   * @return Document converted
   */
  public Document toDocument() {
    Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.trackName).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NAME).setValue(this.artistNames).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.albumName).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.albumArtistNames).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.DATE).setValue(this.albumReleaseDate).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.artistGenres).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.discNumber).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.trackNumber).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.trackDurationMs).createElement());
    // Implement method for boolean values
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(String.valueOf(this.explicit)).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.popularity).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.danceability).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.energy).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.speechiness).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.acousticness).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.instrumentalness).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.liveness).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NUMBER).setValue(this.valence).createElement());

    return documentBuilder.createDocument();
  }

  public int getId() {
    return this.id;
  }

  public String getTrackName() {
    return this.trackName;
  }

  public String getArtistNames() {
    return this.artistNames;
  }

  public String getAlbumName() {
    return this.albumName;
  }

  public String getAlbumArtistNames() {
    return this.albumArtistNames;
  }

  public String getAlbumReleaseDate() {
    return this.albumReleaseDate;
  }

  public String getAlbumImageUrl() {
    return this.albumImageUrl;
  }

  public String getArtistGenres() {
    return this.artistGenres;
  }

  public int getDiscNumber() {
    return this.discNumber;
  }

  public int getTrackNumber() {
    return this.trackNumber;
  }

  public long getTrackDurationMs() {
    return this.trackDurationMs;
  }

  public boolean isExplicit() {
    return this.explicit;
  }

  public int getPopularity() {
    return this.popularity;
  }

  public double getDanceability() {
    return this.danceability;
  }

  public double getEnergy() {
    return this.energy;
  }

  public double getSpeechiness() {
    return this.speechiness;
  }

  public double getAcousticness() {
    return this.acousticness;
  }

  public double getInstrumentalness() {
    return this.instrumentalness;
  }

  public double getLiveness() {
    return this.liveness;
  }

  public double getValence() {
    return this.valence;
  }

  public String getTrackPreviewUrl() {
    return this.trackPreviewUrl;
  }
}
