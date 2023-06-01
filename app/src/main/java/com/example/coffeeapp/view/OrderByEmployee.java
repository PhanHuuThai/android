package com.example.coffeeapp.view;

//import android.support.v7.app.AppCompatActivity;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.*;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.UploadResult;
import com.cloudinary.utils.ObjectUtils;
import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.category;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.ProducctOrderAdapter;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.http.Part;

public class OrderByEmployee extends AppCompatActivity {
    private ProductOrderApiService apiService;
    ProducctOrderAdapter producctOrderAdapter;
    private ImageButton btnAddProduct;
    private RecyclerView rvItems;
    private FloatingActionButton btnAdd;
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu;
    private  String secureUrl ="";
    private List<String> categoryIdList;
    private String lastIdFood, newidFood;

    String idProductOrdered  = "";
    private  String idCategotySelected = "";
    int quantity = 0;
    double salePrice = 0;
    String nameProductOrdered= "";
    private static final int FILE_SELECT_CODE = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_by_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnthongtin= findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderByEmployee.this, information_staff.class);
                startActivity(intent);
            }
        });

        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderByEmployee.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderByEmployee.this, AllStaff.class);
                startActivity(intent);
            }
        });
        btndoanhthu = findViewById(R.id.btn_doanhthu);
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(OrderByEmployee.this, RevenueView.class);
                startActivity(intent);
            }
        });
         categoryIdList = new ArrayList<>();
        apiService = new ProductOrderApiService();
        apiService.GetAllCategory("All").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()
        ).subscribeWith(new DisposableSingleObserver<List<category>>() {
            @Override
            public void onSuccess(@NonNull List<category> categories) {
                for(category item :categories){
                    categoryIdList.add(item.getIdCategory());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        apiService = new ProductOrderApiService();
        List<productOrdered> list = new ArrayList<>();
        rvItems = (RecyclerView) findViewById(R.id.viewEmployees);
        producctOrderAdapter = new ProducctOrderAdapter(list,apiService);
        rvItems.setAdapter(producctOrderAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                apiService.GetAllPBO("All")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onSuccess(@NonNull List<productOrdered> productByOrders) {
                                Log.d("DEBUG","Success");
                                list.removeAll(list);
                                for (productOrdered item :productByOrders
                                ) {
                                    Log.d("DEBUG"," "+item.getName()+" "+item.getImage());
                                    list.add(item);
                                    producctOrderAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d("DEBUG","Fail "+e.getMessage());
                                e.printStackTrace();
                            }
                        });
                handler.postDelayed(this, 500);
            }

        };
        handler.post(runnable);



        btnAdd = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER,list,producctOrderAdapter);
                secureUrl= "";
            }
        });


    }

    private void openFeedbackDialog(int gravity,List<productOrdered> list, ProducctOrderAdapter producctOrderAdapter) {
        final Dialog dialog = new Dialog(btnAdd.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_food);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windownAttribute = window.getAttributes();
        windownAttribute.gravity = gravity;
        window.setAttributes(windownAttribute);
        dialog.show();

        EditText tvQuantity = dialog.findViewById(R.id.ET_getValueNumber);
        EditText tvSalePrice = dialog.findViewById(R.id.txt_getValueSalePrice);
        EditText tvName = dialog.findViewById(R.id.SPN_getValueName);
        Spinner spnCategory = dialog.findViewById(R.id.spnCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryIdList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCategotySelected = spnCategory.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAddProduct = dialog.findViewById(R.id.btn_AddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1 );
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btn_no_thanks);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btnAdd = dialog.findViewById(R.id.btn_send);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = new ProductOrderApiService();
                List<productOrdered> ListFood = new ArrayList<>();
                apiService.GetAllPBO("All")
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                                            @Override
                                            public void onSuccess(@NonNull List<productOrdered> productOrdereds) {
                                                for(productOrdered item : productOrdereds){
                                                    ListFood.add(item);
                                                }
                                                lastIdFood= ListFood.get(ListFood.size()-1).getId();
                                                Log.d("lastidfood",lastIdFood);
                                                newidFood = getIdFood(lastIdFood);
                                                apiService.addProduct(newidFood,String.valueOf(tvName.getText()),
                                                        Double.parseDouble(String.valueOf(tvSalePrice.getText())),Integer.parseInt(String.valueOf(tvQuantity.getText())),
                                                        String.valueOf(secureUrl),idCategotySelected);

                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {

                                            }
                                        });




                Log.d("IMAGE1",secureUrl);
                dialog.dismiss();


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && data != null && data.getData() != null && resultCode!= RESULT_CANCELED) {
            Uri uri = data.getData();
            uploadImage(uri);
        }
    }
    private static String getIdFood(String id){
        String result= "";
        int a = Integer.parseInt(id.substring(2));
        a++;
        if(a < 10){
            result = id.substring(0,2) + "0" + Integer.toString(a);
        }
        else{
            result = id.substring(0,2) + Integer.toString(a);
        }
        return result;
    }
    private void uploadImage(Uri uri) {
                Map config = new HashMap();
        config.put("cloud_name", "dte2ps5qs");
        config.put("api_key", "791388445627371");
        config.put("api_secret", "rNQYUi2PS8ZdL8wKYExiUyZN9_4");
        try {
          MediaManager.init(this,config);
          String requestId = MediaManager.get()
                    .upload(uri)
                    .callback(new UploadCallback() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onStart(String requestId) {
                            Log.d(TAG, "onStart: "+"started");

                        }
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {
                            Log.d(TAG, "onStart: "+"uploading");
                        }
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            Log.d(TAG, "onStart: "+"success");
                            String publicId = (String) resultData.get("public_id");
                            secureUrl = MediaManager.get().url().transformation(new Transformation().width(500).height(500).crop("fill")).format("jpg").secure(true).generate(publicId);
                            Log.d("IMAGE",secureUrl);
                            Picasso.get().load(secureUrl).resize(200,200).into(btnAddProduct);
                        }
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onError(String requestId, ErrorInfo error) {
                            Log.d(TAG, "onStart: "+error);
                        }
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {
                            Log.d(TAG, "onStart: "+error);
                        }
                    }).dispatch();
        }
        catch (Exception e){
            e.printStackTrace();
        }







    }


}