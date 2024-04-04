package usb.finalproject.core.objects.music;

import usb.finalproject.utils.DataReader;
import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.MusicList;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MusicListTest {
    @Test
    public void testNonEmptyMusicList() throws Exception {
        MusicList musicList = DataReader.readMusicListFromJson("src/main/resources/tracks.json");
        List<Music> tracks = musicList.getTracks();
        assertFalse(tracks.isEmpty());
    }

    @Test
    public void testCompleteMusicInfo() throws Exception {
        MusicList musicList = DataReader.readMusicListFromJson("src/main/resources/tracks.json");
        List<Music> tracks = musicList.getTracks();
        for (Music track : tracks) {
            assertNotNull(track.getTrackName());
            assertNotNull(track.getArtistName());
            assertNotNull(track.getAlbumName());
            assertNotNull(track.getAlbumArtistName());
            assertNotNull(track.getAlbumReleaseDate());
            assertNotNull(track.getAlbumImageUrl());
            assertNotNull(track.getArtistGenres());
            assertNotNull(track.getDiscNumber());
            assertNotNull(track.getTrackNumber());
            assertNotNull(track.getTrackDurationMs());
            assertNotNull(track.isExplicit());
            assertNotNull(track.getPopularity());
            assertNotNull(track.getDanceability());
            assertNotNull(track.getEnergy());
            assertNotNull(track.getSpeechiness());
            assertNotNull(track.getAcousticness());
            assertNotNull(track.getInstrumentalness());
            assertNotNull(track.getLiveness());
            assertNotNull(track.getValence());
            assertNotNull(track.getTrackPreviewUrl());
        }
    }

    @Test
    void testNoDuplicateMusic() throws Exception {
        MusicList musicList = DataReader.readMusicListFromJson("src/main/resources/tracks.json");
        List<Music> tracks = musicList.getTracks();
        Set<Music> uniqueTracks = new HashSet<>(tracks);
        assertEquals(tracks.size(), uniqueTracks.size());
    }
}
