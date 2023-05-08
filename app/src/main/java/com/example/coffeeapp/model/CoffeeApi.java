package com.example.coffeeapp.model;

import android.util.Log;

import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.bean.Table;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CoffeeApi {
    @GET("ProductServlet/{key}")
    Single<List<productOrdered>> getPBO(@Path("key") String key);
    @DELETE("ProductServlet/{productId}")
    Single<Void> deletePBO(@Path("productId") String productId);
    @GET("EmployeeServlet/{key}")
    Single<List<Staff>> getStaff(@Path("key") String key);
    @GET("AccountServlet/{account}")
    Single<account> CheckLogin(@Path("account")String account);
    @GET("TableServlet/{key}")
    Single<List<Table>> getTable(@Path("key") String key);
    @GET("OrderServlet/{key}")
    Single<List<Bill>> getBill(@Path("key") String key);
    @POST("OrderServlet/{key}")
    Single<List<BillDetail>> getBillDetail(@Path("key") String key);
    @PUT("ProductServlet")
    Single<Void>addProduct(@Body productOrdered product);
    @GET("CategoryServlet/{key}")
    Single<List<category>> getCategory(@Path("key") String key);
    @PUT("OrderServlet/AddOrder")
    Single<Void>addBill(@Body List<Object> list);
    @PUT("EmployeeServlet/")
    Single<Void>addEmployee(@Body List<Object> staff);
    



}
