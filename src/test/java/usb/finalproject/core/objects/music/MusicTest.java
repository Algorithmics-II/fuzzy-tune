package usb.finalproject.core.objects.music;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usb.finalproject.core.objects.Music;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MusicTest {
  private Music music1;

  @Before
  public void setUp() {
    music1 = new Music(1, "Alone Again", "Gilbert O'Sullivan",
            "Vacation", "Summer", "2023-08-21",
            "http://album.png", "Classic, Musical", 1, 3,
            2400, true, 1, 0.866, 0.921,
            0.2, 0.7, 0.34537, 0.23, 1.0,
            "http://song.mp4");
  }

  @Test
  public void getIdTest() {
    assertEquals(1, music1.getId());
  }

  @Test
  public void getTrackNameTest() {
    assertEquals("Alone Again", music1.getTrackName());
  }

  @Test
  public void getArtistNameTest() {
    assertEquals("Gilbert O'Sullivan", music1.getArtistName());
  }

  @Test
  public void getAlbumNameTest() {
    assertEquals("Vacation", music1.getAlbumName());
  }

  @Test
  public void getAlbumArtistNamesTest() {
    assertEquals("Summer", music1.getAlbumArtistName());
  }

  @Test
  public void getAlbumReleaseDateTest() {
    assertEquals("2023-08-21", music1.getAlbumReleaseDate());
  }

  @Test
  public void getAlbumImageUrlTest() {
    assertEquals("http://album.png", music1.getAlbumImageUrl());
  }

  @Test
  public void getArtistGenresTest() {
    assertEquals("Classic, Musical", music1.getArtistGenres());
  }

  @Test
  public void getDiscNumberTest() {
    assertEquals(1, music1.getDiscNumber());
  }

  @Test
  public void getTrackNumberTest() {
    assertEquals(3, music1.getTrackNumber());
  }

  @Test
  public void getTrackDurationMsTest() {
    assertEquals(2400, music1.getTrackDurationMs());
  }

  @Test
  public void isExplicitTest() {
    assertTrue(music1.isExplicit());
  }

  @Test
  public void getPopularityTest() {
    assertEquals(1, music1.getPopularity());
  }

  @Test
  public void getDanceabilityTest() {
    assertEquals(0.866, music1.getDanceability(), 0);
  }

  @Test
  public void getEnergyTest() {
    assertEquals(0.921, music1.getEnergy(), 0);
  }

  @Test
  public void getSpeechinessTest() {
    assertEquals(0.2, music1.getSpeechiness(), 0);
  }

  @Test
  public void getAcousticnessTest() {
    assertEquals(0.7, music1.getAcousticness(), 0);
  }

  @Test
  public void getInstrumentalnessTest() {
    assertEquals(0.34537, music1.getInstrumentalness(), 0);
  }

  @Test
  public void getLivenessTest() {
    assertEquals(0.23, music1.getLiveness(), 0);
  }

  @Test
  public void getValenceTest() {
    assertEquals(1.0, music1.getValence(), 0);
  }

  @Test
  public void getTrackPreviewUrlTest() {
    assertEquals("http://song.mp4", music1.getTrackPreviewUrl());
  }

  @Test
  public void getTrackInformationTest() {
    String expected = """
            Music:
             Track Name: Alone Again
             Artist Name: Gilbert O'Sullivan
             Album Name: Vacation
             Album Artist Name: Summer
             Album Release Date: 2023-08-21
             Album ImageUrl: http://album.png
             Artist Genres: Classic, Musical
             Disc Number: 1
             Track Number: 3
             Track Duration(ms): 2400
             Explicit: true
             Popularity: 1
             Danceability: 0.866
             Energy: 0.921
             Speechiness: 0.2
             Acousticness: 0.7
             Instrumentalness: 0.34537
             Liveness: 0.23
             Valence: 1.0
             Track Preview URL: http://song.mp4""";

    assertEquals(expected, music1.toString());
  }

  @After
  public void tearDown() {
    music1 = null;
  }
}
