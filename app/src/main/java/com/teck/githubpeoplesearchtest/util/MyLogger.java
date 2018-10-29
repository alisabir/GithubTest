package com.teck.githubpeoplesearchtest.util;

import android.util.Log;

import com.google.gson.Gson;

public class MyLogger {

    private static  String TAG="DEBUG_";

    public static void d(String msg){

        Log.d(TAG,msg);
    }

    public static void d(Object placeOrder) {

        String msg=new Gson().toJson(placeOrder);
        Log.d(TAG,msg);
    }
}
