package usb.finalproject.core.objects.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {
  private User user;
  private List<String> favoriteGenres;
  private List<String> favoriteArtists;
  private List<String> favoriteSongs;
  private List<String> recentlyPlayed;

  @Before
  public void setUp() {
    favoriteGenres = new ArrayList<>();
    favoriteGenres.add("Pop");
    favoriteArtists  = new ArrayList<>();
    favoriteSongs = new ArrayList<>();
    favoriteSongs.add("Noche");
    recentlyPlayed = new ArrayList<>();

    user = new User(1, "Fernando Vaca", 25,
            favoriteGenres, favoriteArtists,
            favoriteSongs, recentlyPlayed);
  }

  @Test
  public void getIdTest() {
    assertEquals(1, user.getId());
  }

  @Test
  public void getNameTest() {
    assertEquals("Fernando Vaca", user.getName());
  }

  @Test
  public void getAgeTest() {
    assertEquals(25, user.getAge());
  }

  @Test
  public void getFavoriteGenresTest() {
    assertEquals(1, user.getFavoriteGenres().size());
    assertEquals("Pop", user.getFavoriteGenres().get(0));
  }

  @Test
  public void getFavoriteArtistsTest() {
    assertEquals(0, user.getFavoriteArtists().size());
  }

  @Test
  public void getFavoriteSongsTest() {
    assertEquals(1, user.getFavoriteSongs().size());
    assertEquals("Noche", user.getFavoriteSongs().get(0));
  }

  @Test
  public void getRecentlyPlayedTest() {
    assertEquals(0, user.getRecentlyPlayed().size());
  }

  @Test
  public void getUserInformationTest() {
    String expected = """
            User:
             Name: Fernando Vaca
             Age: 25
             Favorite Genres: [Pop]
             Favorite Artists: []
             Favorite Songs: [Noche]
             Recently Played: []""";

    assertEquals(expected, user.toString());
  }

  @After
  public void tearDown() {
    user = null;
    favoriteArtists.clear();
    favoriteGenres.clear();
    favoriteSongs.clear();
    recentlyPlayed.clear();
  }
}
