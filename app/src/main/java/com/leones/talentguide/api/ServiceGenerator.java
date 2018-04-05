package com.leones.talentguide.api;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static ApiService API_SERVICE;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    //-----------------------------------------------------------------------------------------------

    //Petitions to api cisco

    public static ApiService dashBoard(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.meraki.com/api/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        API_SERVICE = retrofit.create(ApiService.class);

        return API_SERVICE;
    }

}