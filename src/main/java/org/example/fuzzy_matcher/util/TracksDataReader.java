package org.example.fuzzy_matcher.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.Music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TracksDataReader {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();

        try {
            File file = new File("src/main/resources/tracks.json");
            System.out.println("JSON leído: " + objectMapper.readTree(file));// para verificar que esta leyendo el json
            JsonParser jsonParser = jsonFactory.createParser(file);

            jsonParser.nextToken();

            List<Music> musicList = new ArrayList<>();
            while (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                Music music = objectMapper.readValue(jsonParser, Music.class);
                musicList.add(music);
            }

            // Imprimir la cantidad de pistas en la lista
            System.out.println("Número total de pistas: " + musicList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
