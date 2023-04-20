package com.example.coffeeapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CoffeeApi {
    @GET("ProductByOrderServlet")
    Single<List<productOrdered>> getAllPBO();
    @POST("ProductByOrderServlet/{productId}")
    Call<Void> deletePBO(@Path("productId") String productId);

}
