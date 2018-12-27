package com.gmail.restujulian07.movie_review_app;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncLogin().execute();
    }
    public void viewMovie(View view) {
        Intent movie= new Intent(MainActivity.this, Main2Activity.class);
        startActivity(movie);
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //url = new URL("http://localhost/wservice/film.json");
                //url = new URL("https://gbiirenonprojek.000webhostapp.com/wservice/film.json");
                url = new URL("https://gbiirenonprojek.000webhostapp.com/wservice/film.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //conn.setDoOutput(true);
            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            List<DataMovie> data = new ArrayList<>();

            pdLoading.dismiss();
            try {
                JSONArray jArray = new JSONArray(result);

                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataMovie movieData = new DataMovie();
                    movieData.judulMovie= json_data.getString("judul_film");
                    movieData.deskripsiMovie= json_data.getString("deskripsi");
                    movieData.tglPosting= json_data.getString("tanggal_posting");
                    movieData.gambarMovie= json_data.getString("foto");
                    data.add(movieData);
                }

                RecyclerView rvListMovie = findViewById(R.id.list_movie);
                AdapterMovie mAdapter = new AdapterMovie(MainActivity.this, data);
                rvListMovie.setAdapter(mAdapter);
                rvListMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}