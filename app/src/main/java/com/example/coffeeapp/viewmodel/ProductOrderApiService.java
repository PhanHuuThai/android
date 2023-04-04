package com.example.coffeeapp.viewmodel;

import com.example.coffeeapp.model.CoffeeApi;
import com.example.coffeeapp.model.productOrdered;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductOrderApiService {
    private  static final String baseURL= "http://10.0.2.2:8080/";
    private CoffeeApi api;
    public ProductOrderApiService(){
        api = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CoffeeApi.class);
    }

   public Single<List<productOrdered>> GetAllPBO(){
        return api.getAllPBO();
   }
}
