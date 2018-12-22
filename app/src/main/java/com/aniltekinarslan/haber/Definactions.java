package com.aniltekinarslan.haber;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class Definactions {
    public  static String  BaseUrl = "http://api.moolslife.com";

    public static HaberServices getClient() {
        HaberServices service = null;
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(HaberServices.class);
        }
        return service;
    }
}
