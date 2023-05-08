package com.example.coffeeapp.viewmodel;

import android.util.Log;

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.CoffeeApi;
import com.example.coffeeapp.model.productOrdered;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StaffApiService {
    private  static final String baseURL= "http://103.197.185.4:8081/coffeeapp-api-json/";
    private CoffeeApi api;
    public StaffApiService(){
        api = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CoffeeApi.class);
    }

    public Single<List<Staff>> GetAllStaff(String key){
        Log.d("DEBUG2",key);
        return api.getStaff(key);
    }
    public  Single<Void> addEmployee(List<Object> staffs){
        api.addEmployee(staffs).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Log.d("Add Staff","Success");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Add Staff","Error");
                    }

                });
        return null;
    }


}

