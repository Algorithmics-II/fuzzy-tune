package usb.finalproject.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import usb.finalproject.core.objects.MusicList;
import usb.finalproject.core.objects.UserList;

import java.io.File;
import java.io.IOException;

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

}
