package com.example.week10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView posterImageView;
    private TextView titleTextView, yearTextView, genreTextView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        posterImageView = itemView.findViewById(R.id.posterImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.yearTextView);
        genreTextView = itemView.findViewById(R.id.genreTextView);
    }

    public void bind(Movie movie) {
        titleTextView.setText(movie.getTitle());
        yearTextView.setText(movie.getYear());
        genreTextView.setText(movie.getGenre());

        int posterResId  = itemView.getContext().getResources().getIdentifier(
                movie.getPosterResource(), "drawable", itemView.getContext().getPackageName());

        if (posterResId  == 0) {
            posterImageView.setImageResource(R.drawable.default_poster);
        } else {
            posterImageView.setImageResource(posterResId);
        }
    }
}