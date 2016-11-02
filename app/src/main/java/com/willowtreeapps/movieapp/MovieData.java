package com.willowtreeapps.movieapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by chrisestes on 11/1/16.
 */

public class MovieData {

    private String posterURL;
    private String title;
    private String plot;
    private double rating;
    private String releaseDate;

    public MovieData(JSONObject movieData) throws JSONException {

        posterURL = movieData.getString("poster_path");
        title = movieData.getString("original_title");
        plot = movieData.getString("overview");
        rating = movieData.getDouble("vote_average");
        releaseDate = movieData.getString("release_date");
    }

    public String getPosterPath() {
        return(this.posterURL);
    }
}

/*
        "poster_path":"\/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg",
        "adult":false,
        "overview":"Jack Reacher must uncover the truth behind a major government conspiracy in order to clear his name. On the run as a fugitive from the law, Reacher uncovers a potential secret from his past that could change his life forever.",
        "release_date":"2016-10-19",
        "genre_ids":[53,28,80,18,9648],
        "id":343611,
        "original_title":"Jack Reacher: Never Go Back",
        "original_language":"en",
        "title":"Jack Reacher: Never Go Back",
        "backdrop_path":"\/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg",
        "popularity":30.029323,
        "vote_count":304,
        "video":false,
        "vote_average":4.38}
*/