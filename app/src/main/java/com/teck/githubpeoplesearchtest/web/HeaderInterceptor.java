package com.teck.githubpeoplesearchtest.web;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by fatima on 12/27/2016.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder()
                .addHeader(WebConfig.Headers.API_KEY, "ixZePbqTLpCfiVvkTwLPEHb8kmekJeGJiQRAIAoQ")
//                .addHeader(WebConfig.Headers.SESSION_ID, )
                .addHeader(WebConfig.Headers.CLIENT_TYPE, "Android")
               /// .addHeader(WebConfig.Headers.AUTHORIZATION, AppUtility.genAuthHeader("admin", "1234"))
                .addHeader("Content-Type", "application/json");

        request = builder.build();
        return chain.proceed(request);
    }
}
