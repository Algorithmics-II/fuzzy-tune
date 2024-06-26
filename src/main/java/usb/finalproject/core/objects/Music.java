package usb.finalproject.core.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import usb.finalproject.fuzzy.matcher.domain.Document;
import usb.finalproject.fuzzy.matcher.domain.Element;
import usb.finalproject.fuzzy.matcher.domain.ElementType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Music {
  private int id;
  @JsonProperty("track_name")
  private String trackName;
  @JsonProperty("artist_name(s)")
  private String artistName;
  @JsonProperty("album_name")
  private String albumName;
  @JsonProperty("album_artist_name(s)")
  private String albumArtistName;
  @JsonProperty("album_release_date")
  private String albumReleaseDate;
  @JsonProperty("album_image_url")
  private String albumImageUrl;
  @JsonProperty("artist_genres")
  private String artistGenres;
  @JsonProperty("disc_number")
  private int discNumber;
  @JsonProperty("track_number")
  private int trackNumber;
  @JsonProperty("track_duration(ms)")
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
  @JsonProperty("track_preview_url")
  private String trackPreviewUrl;
  private Document documentPreferencesUser;

  public Music() {
  }

  public Music(int id, String trackName, String artistName, String albumName,
               String albumArtistName, String albumReleaseDate, String albumImageUrl,
               String artistGenres, int discNumber, int trackNumber, long trackDurationMs,
               boolean explicit, int popularity, double danceability, double energy,
               double speechiness, double acousticness, double instrumentalness,
               double liveness, double valence, String trackPreviewUrl) {
    this.id = id;
    this.trackName = trackName;
    this.artistName = artistName;
    this.albumName = albumName;
    this.albumArtistName = albumArtistName;
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
    this.documentPreferencesUser = toDocumentPreferencesMusicToUser();
  }

  /**
   * This method converts the Music class into a Document to be able to make Match.
   * @return Document converted
   */
  public Document toDocument() {
    Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.trackName).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.NAME).setValue(this.artistName).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.albumName).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.albumArtistName).createElement());
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


  /**
   * This method converts the Music class into a Document to be able to make Match.
   * @return Document converted
   */
  private Document toDocumentPreferencesMusicToUser() {
    Document.Builder documentBuilder = new Document.Builder(String.valueOf(this.id));

    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.artistGenres).createElement());
    documentBuilder.addElement(new Element.Builder().setType(ElementType.TEXT).setValue(this.artistName).createElement());

    return documentBuilder.createDocument();
  }

  public void createPreferencesUser() {
    this.documentPreferencesUser = toDocumentPreferencesMusicToUser();
  }

  public Document getDocumentPreferencesUser() {
    if (this.documentPreferencesUser == null)
      this.documentPreferencesUser = toDocumentPreferencesMusicToUser();
    return this.documentPreferencesUser;
  }

  public int getId() {
    return this.id;
  }

  public String getTrackName() {
    return this.trackName;
  }

  public String getArtistName() {
    return this.artistName;
  }

  public String getAlbumName() {
    return this.albumName;
  }

  public String getAlbumArtistName() {
    return this.albumArtistName;
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

  @Override
  public String toString() {
    String songInformation =
            "Music:\n" +
                    " Track Name: " + (trackName != null ? trackName : "N/A") + "\n" +
                    " Artist Name: " + (artistName != null ? artistName : "N/A") + "\n" +
                    " Album Name: " + (albumName != null ? albumName : "N/A") + "\n" +
                    " Album Artist Name: " + (albumArtistName != null ? albumArtistName : "N/A") + "\n" +
                    " Album Release Date: " + (albumReleaseDate != null ? albumReleaseDate : "N/A") + "\n" +
                    " Album ImageUrl: " + (albumImageUrl != null ? albumImageUrl : "N/A") + "\n" +
                    " Artist Genres: " + (artistGenres != null ? artistGenres : "N/A") + "\n" +
                    " Disc Number: " + discNumber + "\n" +
                    " Track Number: " + trackNumber + "\n" +
                    " Track Duration(ms): " + trackDurationMs + "\n" +
                    " Explicit: " + explicit + "\n" +
                    " Popularity: " + popularity + "\n" +
                    " Danceability: " + danceability + "\n" +
                    " Energy: " + energy + "\n" +
                    " Speechiness: " + speechiness + "\n" +
                    " Acousticness: " + acousticness + "\n" +
                    " Instrumentalness: " + instrumentalness + "\n" +
                    " Liveness: " + liveness + "\n" +
                    " Valence: " + valence + "\n" +
                    " Track Preview URL: " + (trackPreviewUrl != null ? trackPreviewUrl : "N/A");

    return songInformation;
  }
}
