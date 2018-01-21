package com.jankrav.letstryretrofit.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jankr on 21.01.2018.
 */

public class GitHubRepoOwner {
    private String login;

    private Integer id;
    private String url;
    private String type;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "owner:" + id.toString() + login;
    }
}
