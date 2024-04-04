package usb.finalproject.core.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MusicList {
    @JsonProperty("tracks")
    private List<Music> tracks;

    public List<Music> getTracks() {
        return tracks;
    }
}
