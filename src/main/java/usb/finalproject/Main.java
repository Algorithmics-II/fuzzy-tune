package usb.finalproject;

import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.MusicList;
import usb.finalproject.core.objects.User;
import usb.finalproject.core.objects.UserList;
import usb.finalproject.core.recommendation.jaro.winker.MusicMatcher;
import usb.finalproject.core.recommendation.jaro.winker.UserMatcher;
import usb.finalproject.core.utils.Printer;
import usb.finalproject.utils.DataReader;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  private static final String USERS = "src/main/resources/users.json";
  private static final String SONGS = "src/main/resources/tracks.json";

  public static void main(String[] args) {
    findSimilarUsers();
    recommendSongs();
  }

  private static void findSimilarUsers() {
    try {
      UserList userList = DataReader.readUsersFromJson(USERS);
      List<User> users = userList.getUsers();
      User targetUser = users.get(20);

      UserMatcher recommendationSystem = new UserMatcher();
      List<User> similarUsers = recommendationSystem.getSimilarUsers(targetUser, users);

      System.out.println("Top 5 similar users to " + targetUser.getName() + ":");
      for (User user : similarUsers) {
        Printer.printUser(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void recommendSongs() {
    try {
      UserList userList = DataReader.readUsersFromJson(USERS);
      MusicList musicList = DataReader.readMusicListFromJson(SONGS);
      List<User> users = userList.getUsers();
      List<Music> musicLibrary = musicList.getTracks();

      User user = users.get(2);

      MusicMatcher recommendationSystem = new MusicMatcher();

      List<Music> recommendedSongs = recommendationSystem.recommendSongs(user, musicLibrary);

      System.out.println("Recommended songs for user " + user.getName() + ":");
      for (Music song : recommendedSongs) {
        Printer.printSong(song);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
