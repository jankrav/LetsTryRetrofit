package com.jankrav.letstryretrofit;

import java.io.Serializable;

/**
 * Created by jankr on 28.12.2017.
 */

public class GitHubRepo implements Serializable {
    private Integer id = null;
    private String name = null;
    private String description = null;

    private Integer watchers = null;

    private String language = null;
    private String default_branch = null;

    public GitHubRepo() {
    }

    public String getLanguage() {
        return language;
    }

    public String getDefaultBranch() {
        return default_branch;
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

    @Override
    public String toString() {
        return id.toString() + " " + name;
    }
}
