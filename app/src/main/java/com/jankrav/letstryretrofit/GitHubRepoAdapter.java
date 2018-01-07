package com.jankrav.letstryretrofit;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by jankr on 29.12.2017.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.ViewRepoNamesHolder> {
    private Context context;
    private List<GitHubRepo> repos;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> repos) {
        this.context = context;
        this.repos = repos;
    }
    @Override
    public ViewRepoNamesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewRepoNamesHolder(inflater.inflate(R.layout.list_item_pagination, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewRepoNamesHolder holder, int position) {
        holder.nameOfRepo.setText(repos.get(position).getName());
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
