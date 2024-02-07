package com.shiend.testdiskominfo.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    private static final String BASE_URL = BaseString.BASE_URL;
    private static Retrofit retrofit;

    private Utils() {
    }

    public static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(800, TimeUnit.SECONDS)
                .writeTimeout(800, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            builder.client(client);
            builder.addConverterFactory(GsonConverterFactory.create(gson));
            retrofit = builder.build();
        }
        return retrofit;
    }
}