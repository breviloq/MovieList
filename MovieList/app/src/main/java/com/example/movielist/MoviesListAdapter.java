package com.example.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.movielist.Movie;

public class MoviesListAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movies;
    private Context context;

    public MoviesListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.movie_item, movies);
        this.movies = movies;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie currentMovie = this.movies.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.movie_item, null, false);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        TextView tvDirector = v.findViewById(R.id.tvDirector);
        TextView tvYear = v.findViewById(R.id.tvYear);

        tvTitle.setText(currentMovie.getTitle());
        tvDirector.setText(currentMovie.getDirector());
        tvYear.setText(String.valueOf(currentMovie.getYear()));

        return v;
    }
}
