package com.jankrav.letstryretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GitHubRepo implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer watchers;
    private String language;
    @SerializedName("default_branch")
    private String defaultBranch;

    @SerializedName("owner")
    private GitHubRepoOwner owner;

    public GitHubRepo() {
    }

    public String getLanguage() {
        return language;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public GitHubRepoOwner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return id.toString() + " " + name;
    }
}
