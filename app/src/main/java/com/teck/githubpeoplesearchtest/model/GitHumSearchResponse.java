package com.teck.githubpeoplesearchtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class GitHumSearchResponse {

    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("incomplete_results")
    private Boolean incompleteResults;
    @SerializedName("items")
    private ArrayList<GithubUser> items = null;

    @SerializedName("total_count")
    public Integer getTotalCount() {
        return totalCount;
    }

    @SerializedName("total_count")
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @SerializedName("incomplete_results")
    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    @SerializedName("incomplete_results")
    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    @SerializedName("items")
    public ArrayList<GithubUser> getItems() {
        return items;
    }

    @SerializedName("items")
    public void setItems(ArrayList<GithubUser> items) {
        this.items = items;
    }



}

