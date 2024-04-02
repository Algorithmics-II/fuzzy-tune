package org.example.proyect.utils;

import org.example.proyect.objects.User;

public class Printer {
    public static void printUser(User user) {
        System.out.println("User recommended: " + user.getName());
        System.out.println("User favorites genres: " + user.getFavoriteGenres());
        System.out.println("User favorite artists: " + user.getFavoriteArtists());
        System.out.println("User favorite songs: " + user.getFavoriteSongs());
        System.out.println("User recently played: " + user.getRecentlyPlayed());
    }
}
