package com.teck.githubpeoplesearchtest.web;



import com.teck.githubpeoplesearchtest.model.GitHumSearchResponse;
import com.teck.githubpeoplesearchtest.model.GithubUser;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiControler {
    private static ApiInterface apiInstance;

    private static void init() {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(WebConfig.URL)
                    .client(getOkHttpClient())
                    .build();



            apiInstance = retrofit.create(ApiInterface.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static okhttp3.OkHttpClient getOkHttpClient() {
        okhttp3.OkHttpClient okClient = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        return okClient;
    }
    public static ApiInterface getApiInstance() {
        if(apiInstance == null) {
            init();
        }
        return apiInstance;
    }


    public static void searchGithubUser(Callback<GitHumSearchResponse> holderCallback,String queryStr){

        Call<GitHumSearchResponse> allProductsRequest = getApiInstance().searchGithubUsers(queryStr);

        allProductsRequest.enqueue(holderCallback);

    }
    public static void loadFollowers(Callback<ArrayList<GithubUser>>callback,String url){


        Call<ArrayList<GithubUser>> allFollowers = getApiInstance().getFollowers(url);

        allFollowers.enqueue(callback);
    }

    public static void loadUserProfile(Callback<GithubUser>callback,String url){


        Call<GithubUser> allFollowers = getApiInstance().loadUserProfile(url);

        allFollowers.enqueue(callback);
    }






}
