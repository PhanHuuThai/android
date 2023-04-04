package com.example.coffeeapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CoffeeApi {
    @GET("ProductByOrderServlet")
    Single<List<productOrdered>> getAllPBO();

}
