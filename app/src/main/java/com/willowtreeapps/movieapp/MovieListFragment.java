package com.willowtreeapps.movieapp;

import android.support.v4.app.Fragment;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by chrisestes on 11/1/16.
 */

public class MovieListFragment extends Fragment {

    private PosterImageAdapter myPosterAdapter;

    private int moviesPage;

    public MovieListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myPosterAdapter = new PosterImageAdapter(getActivity(), R.id.poster_image, new ArrayList<MovieData>());

        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        GridView myGrid = (GridView)rootView.findViewById(R.id.poster_grid);
        myGrid.setAdapter(myPosterAdapter);

        return(rootView);

    }

    @Override
    public void onStart() {
        super.onStart();

        moviesPage = 1;
        getMovies();
    }

    private void getMovies() {
        MoviesFetcher myFetchTask = new MoviesFetcher(myPosterAdapter);
        myFetchTask.execute(Integer.toString(moviesPage));

    }
}
