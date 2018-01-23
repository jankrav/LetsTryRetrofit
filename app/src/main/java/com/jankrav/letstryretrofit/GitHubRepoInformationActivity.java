package com.jankrav.letstryretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.letstryretrofit.client.GitHubClient;
import com.jankrav.letstryretrofit.client.ServiceGenerator;
import com.jankrav.letstryretrofit.model.GitHubRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubRepoInformationActivity extends AppCompatActivity {
    private GitHubRepo gitHubRepo;
    private GitHubClient client;
    private String owner;
    private String repo;

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
        client = ServiceGenerator.getDefaultService();

        Call<GitHubRepo> call = client.repoForUser(owner, repo);


        call.enqueue(new Callback<GitHubRepo>() {
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                gitHubRepo = response.body();

                String str = gitHubRepo.getName();
                if (!TextUtils.isEmpty(str)) name.setText(str);

                str = gitHubRepo.getDescription();
                if (!TextUtils.isEmpty(str)) description.setText(str);

                str = gitHubRepo.getLanguage();
                if (!TextUtils.isEmpty(str)) language.setText(str);

                str = gitHubRepo.getDefaultBranch();
                if (!TextUtils.isEmpty(str)) defaultBranch.setText(str);

                str = gitHubRepo.getWatchers().toString();
                if (!TextUtils.isEmpty(str)) watchers.setText(str);

                Toast.makeText(GitHubRepoInformationActivity.this, "Everyth is OK, Bro", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                Toast.makeText(GitHubRepoInformationActivity.this, "The network call was a failure", Toast.LENGTH_LONG).show();
            }
        });
    }

}
