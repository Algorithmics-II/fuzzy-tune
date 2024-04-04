package usb.finalproject.core.recommendation.fuzzy.matching;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.User;
import usb.finalproject.core.utils.Printer;
import usb.finalproject.fuzzy.matcher.component.MatchService;
import usb.finalproject.fuzzy.matcher.domain.*;

/**
 *This class is responsible for matching between a user and music tracks for recommending music to the user 
 */
public class MusicMatcher {
  private MatchService matchService;
  private List<Match<Document>> musicMatches;

  /**
   * Constructs a MusicMatcher object with the specified MatchService.
   *
   * @param matchService MatchService to apply the match.
   */
  public MusicMatcher(MatchService matchService) {
    this.matchService = matchService;
  }

  /**
     * This method gets the N top matched music tracks for the given user from the provided list of all music tracks.
     *
     * @param user  The user for whom music recommendations are generated.
     * @param allMusic The list of all music tracks available for matching.
     * @return List of Document objects the N top matched music tracks.
     */
  public List<Document> getTopMatchesMusic(User user, List<Music> allMusic) {
    musicMatches = matchUserWithMusic(user, allMusic);
    musicMatches.sort(Comparator.comparing(Match<Document>::getScore, Comparator.comparingDouble(Score::getResult))
        .reversed());

    return musicMatches.stream()
        .limit(10)
        .map(Match::getMatchedWith)
        .collect(Collectors.toList());
  }

  private List<Match<Document>> matchUserWithMusic(User user, List<Music> allMusic) {
    Document userDocument = user.getDocumentRecentlyPlayed();
    List<Document> musicDocuments = allMusic.stream().map(Music::getDocumentTrackName)
        .collect(Collectors.toList());

    Map<Document, List<Match<Document>>> matches = matchService.applyMatch(userDocument, musicDocuments);

    return matches.get(userDocument);
  }

  /**
   * This method prints the recommended music tracks with their scores.
   *
   * @param recommendedMusic The list of recommended music tracks.
   * @param musicList List of all music tracks.
   */
  public void printRecommendedMusic(List<Document> recommendedMusic, List<Music> musicList) {
    int index = 0;
    for (Document document : recommendedMusic) {
      for (Music music : musicList) {
        if (document.getKey().equals(String.valueOf(music.getId()))) {
          Printer.printSong(music);
          System.out.println("Score match: " + musicMatches.get(index++).getScore().getResult());
        }
      }
      System.out.println("--------------------");
    }
  }

  /**
   * This method gets the list of music matches.
   *
   * @return List of music matches.
   */
  public List<Match<Document>> getMusicMatches() {
    return musicMatches;
  }
}
