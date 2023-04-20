package com.example.coffeeapp.viewmodel;

import android.util.Log;

import com.example.coffeeapp.model.CoffeeApi;
import com.example.coffeeapp.model.productOrdered;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
   public void deletePBO(String resourceId) {
       Log.d("DEBUG","22222");
        Call<Void> call = api.deletePBO(resourceId);
        call.enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               Log.d("DEBUG","thanh cong");
           }
            @Override
           public void onFailure(Call<Void> call, Throwable t) {
                Log.d("DEBUG","chua thanh cong");
           }
       });
    }
}
