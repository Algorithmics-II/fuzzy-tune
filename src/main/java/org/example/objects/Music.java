package org.example.objects;

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
}
