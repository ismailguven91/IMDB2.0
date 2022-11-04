package com.example.application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment3 extends Fragment {

    public Fragment3() {

    }

    View view;
    TextView theTitle;
    TextView theType;
    TextView theDirector;
    TextView theYear;
    ImageView theMovieImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_3, container, false);



        Bundle bundle = this.getArguments();

        // FINDS THE ID FROM XML
        theTitle = view.findViewById(R.id.theTitle);
        theType = view.findViewById(R.id.theType);
        theDirector = view.findViewById(R.id.theDirector);
        theYear = view.findViewById(R.id.theYear);
        theMovieImage = view.findViewById(R.id.movieImage);

        if (bundle != null) {

            String theMovieName = bundle.getString("movieName");
            ExecuteTaskInBackGround ex2 = new ExecuteTaskInBackGround(theMovieName);
            ex2.execute();
        }


        return view;

    }

    private class ExecuteTaskInBackGround extends AsyncTask<Void, Void, Void> {


        String search = "";

        public ExecuteTaskInBackGround(String userInput){
            search = userInput;
        }



        @Override
        protected Void doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://imdb8.p.rapidapi.com/auto-complete?q="+search)
                    .get()
                    .addHeader("X-RapidAPI-Key", "36c63a31f4msh739aecdc5496ecap1a049cjsn0204fb8ff207")
                    .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();

                JSONObject jsonResponse = new JSONObject(response.body().string());

                JSONArray jsonArrayD = jsonResponse.getJSONArray("d");

                JSONObject obj0 = jsonArrayD.getJSONObject(0);


                JSONObject objI = obj0.getJSONObject("i");
                Log.d("objI", objI.toString());



                String imgUrl = objI.getString("imageUrl");
                Log.d("imgUrl", imgUrl);


                // THE MOVIE NAME
                String movieName = obj0.getString("l");
                theTitle.setText(movieName);

                // THE TYPE
                if (obj0.has("qid")){
                    String movieType = obj0.getString("qid");
                    theType.setText(movieType);
                }


                    // TO SKIP THE MOVIE YEAR IF IT DOESNT EXIST
                if (obj0.has("y")) {
                    String movieYear = obj0.getString("y");
                    theYear.setText(movieYear);

                }

                // TO SKIP THE DIRECTOR YEAR IF IT DOESNT EXIST
                if (obj0.has("s")) {
                    String movieDirector = obj0.getString("s");
                    theDirector.setText(movieDirector);

                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }







    }

}

