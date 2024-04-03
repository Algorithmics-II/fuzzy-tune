package org.example.fuzzy_matcher.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.Music;
import org.example.objects.MusicList;
import org.example.objects.User;
import org.example.objects.UserList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataReader {

    public static MusicList readMusicListFromJson(String filePath) throws Exception {
        File jsonFile = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonFile, MusicList.class);
    }

    public static UserList readUsersFromJson(String filePath) {
        try {
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonFile, UserList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        UserList userList = readUsersFromJson("src/main/resources/users.json");
        List<User> users = userList.getUsers();//lista de usuarios que contiene la informacion de cada usuario
        System.out.println(users.size());

        MusicList musicList = readMusicListFromJson("src/main/resources/tracks.json");
        List<Music> tracks = musicList.getTracks(); // lista de tracks que contiene la informacion de cada track
        System.out.println(tracks.size());

    }
}
