package com.willowtreeapps.movieapp;

import android.net.NetworkRequest;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisestes on 11/1/16.
 */

public class MoviesFetcher extends AsyncTask<String, Void, Void>{

    private PosterImageAdapter myPosterAdapter;
    private List<MovieData> myMoviesList;

    public MoviesFetcher(PosterImageAdapter theAdapter) {
        super();
        myPosterAdapter = theAdapter;
    }

    @Override
    protected Void doInBackground(String... strings) {

        HttpURLConnection myConnection = null;
        BufferedReader myReader = null;

        String queryResults = null;

        String[] posterPaths = null;



        try {




            final String SEARCH_BASE_URL = "http://api.themoviedb.org/3";
            final String SEARCH_PATH = "/discover/movie";

            final String SORT_KEY = "sort_by";
            final String SORT_BY_POPULARITY = "popularity.desc";

            final String API_KEY_KEY = "api_key";
            final String API_KEY_VALUE = "f7a9e5661c979c8473b151e5dc2d21ce";
            final String PAGE_KEY = "page";

            Uri myURI = Uri.parse(SEARCH_BASE_URL).buildUpon()
                    .appendPath("discover")
                    .appendPath("movie")
                    .appendQueryParameter(PAGE_KEY, strings[0])
                    .appendQueryParameter(API_KEY_KEY, API_KEY_VALUE)
                    .appendQueryParameter(SORT_KEY, SORT_BY_POPULARITY)
                    .build();

            URL myURL = new URL(myURI.toString());

            myConnection = (HttpURLConnection) myURL.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.connect();

            InputStream myInStream = myConnection.getInputStream();
            StringBuffer myInBuffer = new StringBuffer();

            if (myInStream != null) {

                myReader = new BufferedReader(new InputStreamReader(myInStream));

                String line;

                do {


                    line = myReader.readLine();

                    myInBuffer.append(line + "\n");

                } while (line != null);

                if (myInBuffer.length() > 0) {
                    queryResults = myInBuffer.toString();
                }
            }

        } catch (IOException e) {
            Log.e("MoviesFetcher", "URL error", e);

        } finally {

            if (myConnection != null) {

                myConnection.disconnect();
            }

            if (myReader != null) {
                try {
                    myReader.close();
                } catch (final IOException e) {
                    Log.e("MoviesFetcher", "Reader close failure", e);

                }
            }
        }

        try {
           myMoviesList =  getPosterURLsFromJSON(queryResults);

        } catch (JSONException e){
            Log.e("here", "JSON error", e);

        }

        return(null);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        myPosterAdapter.clear();
        myPosterAdapter.addAll(myMoviesList);
    }

    private List<MovieData> getPosterURLsFromJSON(String queryResults)
        throws JSONException {

            ArrayList<MovieData> myMoviesList = new ArrayList<MovieData>() {};


        JSONObject rawJSON = new JSONObject(queryResults);
            JSONArray moviesArray = rawJSON.getJSONArray("results");
            JSONObject myMovie;



            for(int xLoop = 0; xLoop < moviesArray.length(); xLoop++) {
                myMovie = moviesArray.getJSONObject(xLoop);
                myMoviesList.add(new MovieData(myMovie));
            }

            return(myMoviesList);

        }

    }

