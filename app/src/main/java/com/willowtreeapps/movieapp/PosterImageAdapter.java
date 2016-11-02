package com.willowtreeapps.movieapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by chrisestes on 11/1/16.
 */

public class PosterImageAdapter extends ArrayAdapter<MovieData> {

    private ArrayList<MovieData> myList;
    private ImageView myImage;
    private Context myContext;

    final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_SIZE = "w342";

    public PosterImageAdapter(Context theContext, int theResources, ArrayList<MovieData> theList) {
        super(theContext, theResources, theList);

        this.myContext = theContext;
        this.myList = theList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.poster_image, parent, false);
        }

        myImage = (ImageView)convertView.findViewById(R.id.poster_image);

        if(myList.size() > position) {
            Picasso.with(myContext).load(IMAGE_BASE_URL + POSTER_SIZE + myList.get(position).getPosterPath()).into(myImage);
        }


        return(convertView);
    }

}
