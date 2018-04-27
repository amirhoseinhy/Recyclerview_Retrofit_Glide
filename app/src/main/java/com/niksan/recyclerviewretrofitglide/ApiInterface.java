package com.niksan.recyclerviewretrofitglide;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/recycler_retrofit_loadimage/json")
    Call<List<Product>> getProductsDetails();
}
