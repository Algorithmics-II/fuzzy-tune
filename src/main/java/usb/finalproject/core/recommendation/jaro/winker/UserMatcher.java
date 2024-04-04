package usb.finalproject.core.recommendation.jaro.winker;

import usb.finalproject.core.objects.User;
import usb.finalproject.jaro.winkler.JaroWinkler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMatcher {
  public List<User> getSimilarUsers(User targetUser, List<User> users) {
    List<User> similarUsers = new ArrayList<>();
    List<String> targetGenres = targetUser.getFavoriteGenres();
    Map<User, Double> similarityMap = new HashMap<>();

    for (User user : users) {
      if (user != targetUser) {
        List<String> userGenres = user.getFavoriteGenres();
        double totalSimilarity = 0.0;
        int count = 0;

        for (String targetGenre : targetGenres) {
          for (String userGenre : userGenres) {
            double similarity = JaroWinkler.jaroWinklerSimilarity(targetGenre, userGenre);
            totalSimilarity += similarity;
            count++;
          }
        }

        double averageSimilarity = count > 0 ? totalSimilarity / count : 0.0;
        similarityMap.put(user, averageSimilarity);
      }
    }

    similarityMap.entrySet().stream()
            .sorted(Map.Entry.<User, Double>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> similarUsers.add(entry.getKey()));

    return similarUsers;
  }
}
