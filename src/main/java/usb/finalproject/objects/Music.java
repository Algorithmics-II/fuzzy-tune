package usb.finalproject.objects;

import java.util.List;

public class Music {
  private int id;
  private String trackName;
  private String artistName;
  private String albumName;
  private String albumArtistName;
  private String albumReleaseDate;
  private String albumImageUrl;
  private List<String> artistGenres;
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

  public Music(int id, String trackName, String artistName, String albumName,
               String albumArtistName, String albumReleaseDate, String albumImageUrl,
               List<String> artistGenres, int discNumber, int trackNumber, long trackDurationMs,
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

  public List<String> getArtistGenres() {
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
