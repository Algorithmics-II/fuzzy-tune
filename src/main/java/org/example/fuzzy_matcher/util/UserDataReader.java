package org.example.fuzzy_matcher.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.User;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserDataReader {
    public static List<User> readUsersFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Leer el JSON desde un archivo
            File file = new File(filePath);

            // Convertir el JSON a una lista de objetos User
            User[] usersArray = objectMapper.readValue(file, User[].class);
            return Arrays.asList(usersArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        List<User> users = readUsersFromFile("src/main/resources/users.json");

        if (users != null) {
            for (User user : users) {
                System.out.println("User ID: " + user.getId());
                //System.out.println("Name: " + user.getName());
                //System.out.println("Age: " + user.getAge());
                //System.out.println("Favorite Genres: " + user.getFavoriteGenres());
                //System.out.println("Favorite Artists: " + user.getFavoriteArtists());
                //System.out.println("Favorite Songs: " + user.getFavoriteSongs());
                //System.out.println("Recently Played: " + user.getRecentlyPlayed());
                //System.out.println();
            }
        } else {
            System.out.println("No se pudieron leer los usuarios correctamente.");
        }
    }
}
