package com.jankrav.letstryretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static String API_BASE_URL = "https://api.github.com";
    private GitHubClient client;
    private Retrofit retrofit;

    private OkHttpClient.Builder httpClient;
    private Retrofit.Builder builder;
    //private ListView listView;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.pagination_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );
        retrofit =
                builder
                        .client(httpClient.build())
                        .build();

        //Create a very simple REST adapter which points the Github API endpoint
        client = retrofit.create(GitHubClient.class);

        //Fetch a list of the GITHUB repositiries
        Call<List<GitHubRepo>> call = client.reposForUser("jankrav");

        //Execute the call asynchronously. Get a positive or negative callback
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                //the network call was a success and we got a response
                //TODO: use the repositiry list and display it

                List<GitHubRepo> repos = response.body();
                Toast.makeText(MainActivity.this, "Vse ok, Bro", Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                //the network call was a failure
                //TODO: handle error
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG);
            }
        });
    }
}
