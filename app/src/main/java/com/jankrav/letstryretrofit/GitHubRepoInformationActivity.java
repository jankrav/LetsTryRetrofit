package com.jankrav.letstryretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GitHubRepoInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_information);

//        GitHubRepo gitHubRepo = new GitHubRepo();

        GitHubRepo gitHubRepo  =
                (GitHubRepo) getIntent().getSerializableExtra("GitHubRepo");

        TextView name = (TextView) findViewById(R.id.name);
        TextView description = (TextView) findViewById(R.id.description);
        TextView language = (TextView) findViewById(R.id.language);
        TextView defaultBranch = (TextView) findViewById(R.id.defaultBranch);
        TextView watchers = (TextView) findViewById(R.id.watchers);

        name.setText(gitHubRepo.getName());
        description.setText(gitHubRepo.getDescription());


        language.setText(gitHubRepo.getLanguage());
        defaultBranch.setText(gitHubRepo.getDefaultBranch());
        watchers.setText(gitHubRepo.getWatchers().toString());
    }
}
