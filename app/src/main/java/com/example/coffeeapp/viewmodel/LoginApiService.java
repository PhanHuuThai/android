package com.example.coffeeapp.viewmodel;

import android.util.Log;
import android.widget.Toast;

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.CoffeeApi;
import com.example.coffeeapp.model.account;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.view.Login;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiService  {
    private  static final String baseURL= "http://103.197.185.4:8081/coffeeapp-api-json_war/";
    private CoffeeApi api;
    public LoginApiService(){
        api = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CoffeeApi.class);
    }
    public Single<account> CheckLogin(String acc) {Log.d("DEBUG",acc);
       return  api.CheckLogin(acc);
    }
}
