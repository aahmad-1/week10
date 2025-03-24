package com.example.week10;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();

        try {
            String json = readJsonFile(context, R.raw.movies);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Movie movie = parseMovie(obj);
                    if (movie != null) {
                        movies.add(movie);
                    }
                } catch (JSONException e) {
                    handleJsonException(e);
                }
            }
        } catch (Exception e) {
            handleJsonException(e);
        }

        return movies;
    }

    private static void handleJsonException(Exception e) {
        Log.e("JsonUtils", "Error reading the JSON data", e);
    }

    private static String readJsonFile(Context context, int resourceId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    private static Movie parseMovie(JSONObject obj) throws JSONException {
        String title = obj.optString("title", null);
        Integer year = parseYear(obj.opt("year"));
        String genre = obj.optString("genre", null);
        String poster = obj.optString("poster", null);

        if (title == null && year == null && genre == null && poster == null) {
            return null;
        }

        return new Movie(title, year, genre, poster);
    }

    private static Integer parseYear(Object yearObj) {
        if (yearObj == null) return null;

        try {
            if (yearObj instanceof Number) {
                double yearValue = ((Number) yearObj).doubleValue();
                if (yearValue == (int) yearValue) {
                    return Math.abs((int) yearValue);
                }
                return null;
            } else if (yearObj instanceof String) {
                try {
                    return Math.abs(Integer.parseInt((String) yearObj));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } catch (Exception e) {
            Log.w("JsonUtils", "Error parsing year", e);
        }
        return null;
    }
}