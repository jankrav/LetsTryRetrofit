package com.jankrav.letstryretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.letstryretrofit.client.GitHubClient;
import com.jankrav.letstryretrofit.client.ServiceGenerator;
import com.jankrav.letstryretrofit.model.GitHubRepo;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private GitHubClient client;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView login = (TextView) findViewById(R.id.login);
        final ImageView avatarPhoto = (ImageView) findViewById(R.id.avatarPhoto);

        recyclerView = (RecyclerView) findViewById(R.id.pagination_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //creating retrofit client
        client = ServiceGenerator.getDefaultService();

        //Fetch a list of the GITHUB repositiries
        Call<List<GitHubRepo>> call = client.reposForUser("jankrav");

        //Execute the call asynchronously
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                //show avatar photo of owner
                Picasso
                        .with(MainActivity.this)
                        .load(repos.get(0).getOwner().getAvatarUrl())
                        .into(avatarPhoto);

                login.setText(repos.get(0).getOwner().getLogin());
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
