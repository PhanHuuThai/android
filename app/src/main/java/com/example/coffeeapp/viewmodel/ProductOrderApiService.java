package com.example.coffeeapp.viewmodel;

import android.util.Log;

import com.example.coffeeapp.model.CoffeeApi;
import com.example.coffeeapp.model.category;
import com.example.coffeeapp.model.productOrdered;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductOrderApiService {
    private  static final String baseURL= "http://103.197.185.4:8081/coffeeapp-api-json/";

    private CoffeeApi api;
    public ProductOrderApiService(){
        api = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CoffeeApi.class);
    }

   public Single<List<productOrdered>> GetAllPBO(String key){
        return api.getPBO(key);
   }
    public Single<List<category>> GetAllCategory(String key){
        return api.getCategory(key);
    }
   public void deletePBO(String resourceId) {
        Single<Void> call = api.deletePBO(resourceId);
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Log.d("DEBUG","ThanhCong");
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","ThatBai "+e.getMessage());
                    }
                });;
    }
    public  void addProduct(String id,String name,double salePrice,int quantity,String image,String idCategory){
        productOrdered productOrdered = new productOrdered(id, name, salePrice, quantity, image,idCategory);
        Single<Void> call = api.addProduct(productOrdered);
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Log.d("DEBUG","ThanhCong");
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","ThatBai "+e.getMessage());
                    }
                });;
    }
}
