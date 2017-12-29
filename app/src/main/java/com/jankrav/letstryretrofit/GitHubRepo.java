package com.jankrav.letstryretrofit;

/**
 * Created by jankr on 28.12.2017.
 */

public class GitHubRepo {
    private int id;
    private String name;

    public GitHubRepo() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "" + id + " " + name;
    }
}
