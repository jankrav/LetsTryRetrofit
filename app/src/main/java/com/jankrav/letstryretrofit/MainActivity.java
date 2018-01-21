package com.jankrav.letstryretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jankrav.letstryretrofit.client.GitHubClient;
import com.jankrav.letstryretrofit.model.GitHubRepo;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.pagination_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());


        retrofit = builder.build();

        //Create a very simple REST adapter which points the Github API endpoint
        client = retrofit.create(GitHubClient.class);

        //Fetch a list of the GITHUB repositiries
        Call<List<GitHubRepo>> call = client.reposForUser("octocat");

        //Execute the call asynchronously. Get a positive or negative callback
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                Toast.makeText(MainActivity.this, "Everyth is OK, Bro", Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new GitHubRepoAdapter(repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "The network call was a failure", Toast.LENGTH_LONG);
            }

        });
    }
}
