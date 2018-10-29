package com.teck.githubpeoplesearchtest.web;


import com.teck.githubpeoplesearchtest.model.GitHumSearchResponse;
import com.teck.githubpeoplesearchtest.model.GithubUser;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {


    @GET("search/users?")
    Call<GitHumSearchResponse> searchGithubUsers(@Query("q")  String queryStr);

    @GET()
    Call<ArrayList<GithubUser>> getFollowers(@Url  String url);

    @GET()
    Call<GithubUser> loadUserProfile(@Url  String url);


}
