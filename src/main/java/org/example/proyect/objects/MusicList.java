package org.example.proyect.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.proyect.objects.Music;

import java.util.List;

public class MusicList {
    @JsonProperty("tracks")
    private List<Music> tracks;

    public List<Music> getTracks() {
        return tracks;
    }
}
