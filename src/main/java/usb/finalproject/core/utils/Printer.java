package usb.finalproject.core.utils;

import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.User;

public class Printer {
    /**
     * This method prints the user information.
     * @param user User to print
     */
    public static void printUser(User user) {
        System.out.println("User recommended: " + user.getName());
        System.out.println("User favorites genres: " + user.getFavoriteGenres());
        System.out.println("User favorite artists: " + user.getFavoriteArtists());
        System.out.println("User favorite songs: " + user.getFavoriteSongs());
        System.out.println("User recently played: " + user.getRecentlyPlayed());
    }

    /**
     * This method prints the song information.
     * @param music Music to print
     */
    public static void printSong(Music music) {
        System.out.println("Song recommended: " + music.getTrackName());
        System.out.println("Artist: " + music.getArtistName());
        System.out.println("Genres: " + music.getArtistGenres());
    }
}
