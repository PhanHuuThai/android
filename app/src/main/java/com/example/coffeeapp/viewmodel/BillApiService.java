package com.example.coffeeapp.viewmodel;

import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.model.CoffeeApi;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BillApiService {
    private static final String baseURL= "http://103.197.185.4:8081/coffeeapp-api-json/";
    private CoffeeApi api;
    public BillApiService(){
        api = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CoffeeApi.class);
    }

    public Single<List<Bill>> getAllBill(String key){
        return api.getBill(key);
    }

}
