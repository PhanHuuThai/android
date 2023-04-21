package com.example.coffeeapp.model;

import android.util.Log;

import com.example.coffeeapp.bean.Staff;

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
    @GET("ProductByOrderServlet/{key}")
    Single<List<productOrdered>> getPBO(@Path("key") String key);
    @DELETE("ProductByOrderServlet/{productId}")
    Single<Void> deletePBO(@Path("productId") String productId);
    @GET("EmployeeServlet/{key}")
    Single<List<Staff>> getStaff(@Path("key") String key);
    @POST("EmployeeServlet/{account}")
    Single<account> CheckLogin(@Path("account")String account);
}
