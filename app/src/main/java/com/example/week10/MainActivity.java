package com.example.week10;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        setupRecyclerView();
        loadMovieData();
    }

    private void setupRecyclerView() {
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(movies);
        movieRecyclerView.setAdapter(adapter);
    }

    private void loadMovieData() {
        try {
            movies = JsonUtils.loadMoviesFromJson(this);
            if (movies == null || movies.isEmpty()) {
                showError("No movies found or error loading data.");
            } else {
                adapter.updateMovies(movies);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error loading movie data from JSOSN", e);
            showError("Failed to load movie data. Please try again.");
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}