package com.teck.githubpeoplesearchtest.web;

/**
 * Created by fatima on 12/22/2016.
 */

public class WebConfig {
    public static final String URL                                          = "https://api.github.com/";

    public static class Headers {
        public static final String API_KEY                                  = "api-key";
        public static final String SESSION_ID                               = "session-id";
        public static final String CLIENT_TYPE                              = "client-type";
        public static final String AUTHORIZATION                            = "Authorization";
    }

    public static class Multipart {
        public static final String MULTIPART_FORM_DATA                       = "multipart/form-data";
    }
}