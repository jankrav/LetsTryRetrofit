package com.jankrav.letstryretrofit;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;


/**
 * Created by jankr on 29.12.2017.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.ViewRepoNamesHolder> {
    private Context context;
    private List<GitHubRepo> repos;

    public GitHubRepoAdapter(List<GitHubRepo> repos) {
        this.repos = repos;
    }

    @Override
    public ViewRepoNamesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewRepoNamesHolder(inflater.inflate(R.layout.list_item_pagination, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewRepoNamesHolder holder, final int position) {
        holder.nameOfRepo.setText(repos.get(position).getName());

        holder.nameOfRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GitHubRepoInformationActivity.class);
                intent.putExtra("GitHubRepo", repos.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    protected class ViewRepoNamesHolder extends RecyclerView.ViewHolder {
        TextView nameOfRepo;

        public ViewRepoNamesHolder(View itemView) {
            super(itemView);
            nameOfRepo = itemView.findViewById(R.id.list_item_pagination_text);
        }
    }
}
