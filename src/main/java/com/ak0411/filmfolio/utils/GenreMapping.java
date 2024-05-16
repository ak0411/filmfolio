package com.ak0411.filmfolio.utils;

import java.util.HashMap;
import java.util.Map;

public class GenreMapping {

    private static final Map<Integer, String> GENRES = new HashMap<>();

    static {
        // TMDB Movie genres
        GENRES.put(28, "Action");
        GENRES.put(12, "Adventure");
        GENRES.put(16, "Animation");
        GENRES.put(35, "Comedy");
        GENRES.put(80, "Crime");
        GENRES.put(99, "Documentary");
        GENRES.put(18, "Drama");
        GENRES.put(10751, "Family");
        GENRES.put(14, "Fantasy");
        GENRES.put(36, "History");
        GENRES.put(27, "Horror");
        GENRES.put(10402, "Music");
        GENRES.put(9648, "Mystery");
        GENRES.put(10749, "Romance");
        GENRES.put(878, "Science Fiction");
        GENRES.put(10770, "TV Movie");
        GENRES.put(53, "Thriller");
        GENRES.put(10752, "War");
        GENRES.put(37, "Western");

        // TV genres
        GENRES.put(10759, "Action & Adventure");
        GENRES.put(10762, "Kids");
        GENRES.put(9648, "Mystery");
        GENRES.put(10763, "News");
        GENRES.put(10764, "Reality");
        GENRES.put(10765, "Sci-Fi & Fantasy");
        GENRES.put(10766, "Soap");
        GENRES.put(10767, "Talk");
        GENRES.put(10768, "War & Politics");
    }

    public static String getGenre(Integer id) {
        return GENRES.get(id);
    }
}
