package com.gwr.retrofit2example;

import com.gwr.retrofit2example.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by willi on 01/08/2016.
 */
public interface UdacityService {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();
}
