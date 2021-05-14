package com.quircode.testcarso.Net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNetClient {
    public static final String URL_BASE = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "57915ae446c117b3f3b82dd03ebf8d67";
    public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w1280";

    public static Retrofit retrofit = null;

    public static Retrofit getClient () {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
