package usb.finalproject.core.recommendation.jaro.winker;

import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class responsible for recommending songs based on user preferences and music library.
 */
public class MusicMatcher {

  /**
   * Recommends songs to a user based on their preferences and the music library.
   *
   * @param user         The user for whom songs are recommended.
   * @param musicLibrary The music library containing available songs.
   * @return A list of recommended songs for the user.
   */
  public List<Music> recommendSongs(User user, List<Music> musicLibrary) {
    List<String> userFavoriteGenres = user.getFavoriteArtists().stream()
            .map(artistName -> getGenresForArtist(artistName, musicLibrary))
            .flatMap(List::stream)
            .distinct()
            .collect(Collectors.toList());

    List<Music> filteredMusic = musicLibrary.stream()
            .filter(music -> matchesGenres(music.getArtistGenres(), userFavoriteGenres))
            .toList();

    List<String> recentlyPlayed = user.getRecentlyPlayed();

    List<Music> recommendedSongs = filteredMusic.stream()
            .filter(music -> !recentlyPlayed.contains(music.getTrackName()))
            .limit(20)
            .collect(Collectors.toList());

    return recommendedSongs;
  }

  /**
   * Retrieves the genres associated with a given artist from the music library.
   *
   * @param artistName   The name of the artist.
   * @param musicLibrary The music library containing information about artists and genres.
   * @return A list of genres associated with the artist.
   */
  private List<String> getGenresForArtist(String artistName, List<Music> musicLibrary) {
    List<String> genres = new ArrayList<>();

    for (Music music : musicLibrary) {
      if (music.getArtistName().equalsIgnoreCase(artistName)) {
        genres.addAll(Arrays.asList(music.getArtistGenres().split(",")));
      }
    }

    return genres;
  }

  /**
   * Checks if the genres of a music match with the user's favorite genres.
   *
   * @param musicGenres The genres associated with the music.
   * @param userGenres  The user's favorite genres.
   * @return True if there is a match, false otherwise.
   */
  private boolean matchesGenres(String musicGenres, List<String> userGenres) {
    List<String> musicGenreList = Arrays.asList(musicGenres.split(","));

    for (String genre : userGenres) {
      if (musicGenreList.contains(genre.trim())) {
        return true;
      }
    }
    return false;
  }
}
