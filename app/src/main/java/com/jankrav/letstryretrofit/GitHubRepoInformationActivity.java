package com.jankrav.letstryretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.letstryretrofit.model.GitHubRepo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRepoInformationActivity extends AppCompatActivity {
    private GitHubRepo gitHubRepo;
    private String owner;
    private String repo;

    private static String API_BASE_URL = "https://api.github.com";
    private GitHubClient client;
    private Retrofit retrofit;
    private OkHttpClient.Builder httpClient;
    private Retrofit.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_information);

        final TextView name = (TextView) findViewById(R.id.name);
        final TextView description = (TextView) findViewById(R.id.description);
        final TextView language = (TextView) findViewById(R.id.language);
        final TextView defaultBranch = (TextView) findViewById(R.id.defaultBranch);
        final TextView watchers = (TextView) findViewById(R.id.watchers);

        //get data about user
        repo = getIntent().getStringExtra("REPO");
        owner = getIntent().getStringExtra("OWNER");

        //creating retrofit client
        httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());
        retrofit = builder.build();
        client = retrofit.create(GitHubClient.class);

        Call<GitHubRepo> call = client.repoForUser(owner, repo);
        call.enqueue(new Callback<GitHubRepo>() {
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                gitHubRepo = response.body();

                name.setText(gitHubRepo.getName());
                description.setText(gitHubRepo.getDescription());


                language.setText(gitHubRepo.getLanguage());
                defaultBranch.setText(gitHubRepo.getDefaultBranch());
                watchers.setText(gitHubRepo.getWatchers().toString());

                Toast.makeText(GitHubRepoInformationActivity.this, "Everyth is OK, Bro", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                Toast.makeText(GitHubRepoInformationActivity.this, "The network call was a failure", Toast.LENGTH_LONG);
            }
        });
    }

}
