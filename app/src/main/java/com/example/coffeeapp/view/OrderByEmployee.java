package com.example.coffeeapp.view;

//import android.support.v7.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.R;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.Product;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.ProducctOrderAdapter;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderByEmployee extends AppCompatActivity {
    private ProductOrderApiService apiService;
    ProducctOrderAdapter producctOrderAdapter;
    private RecyclerView rvItems;
    private FloatingActionButton btnAdd;
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu;

    int idProductOrdered  = 0;
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


        apiService = new ProductOrderApiService();
        List<productOrdered> list = new ArrayList<>();
        rvItems = (RecyclerView) findViewById(R.id.viewEmployees);
        producctOrderAdapter = new ProducctOrderAdapter(list,apiService);
        rvItems.setAdapter(producctOrderAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        apiService.GetAllPBO("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(@NonNull List<productOrdered> productByOrders) {
                        Log.d("DEBUG","Success");
                        for (productOrdered item :productByOrders
                             ) {
                            Log.d("DEBUG"," "+item.getName());
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

        btnAdd = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER,list,producctOrderAdapter);
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
        Button btnAddProduct = dialog.findViewById(R.id.btn_AddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
//                    Toast.makeText(this, "Please install a File Manager.",
//                            Toast.LENGTH_SHORT).show();

                }
            }
        });
//        List<Product> listProducts = new ArrayList<>();
//        listProducts.add(new Product(1,"Cà phê đen",18000,5,""));
//        listProducts.add(new Product(2,"Cà phê sữa",20000,10,""));
//        listProducts.add(new Product(3,"Trà Sữa",30000,5,""));
//        listProducts.add(new Product(4,"Bạc xĩu",25000,5,""));
//        List<String> listNameProduct = new ArrayList<>();
//        for (Product item: listProducts
//             ) {
//            listNameProduct.add(item.getName());
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listNameProduct);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        tvName.setAdapter(adapter);
//        tvName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                             @Override
//                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                                 if(!tvName.getSelectedItem().toString().equals("")) {
//                                                     for (Product item : listProducts
//                                                     ) {
//                                                         if (item.getName().equals(tvName.getSelectedItem().toString())) {
//                                                             tvSalePrice.setText(String.valueOf(item.getSalePrice()));
//                                                             idProductOrdered = item.getId();
//                                                             nameProductOrdered = item.getName();
//                                                             salePrice = item.getSalePrice();
//                                                             break;
//                                                         }
//                                                     }
//
//                                                 }
//
//                                             }
//                                             @Override
//                                             public void onNothingSelected(AdapterView<?> parent) {
//
//                                             }
//                                         });
//        if(!tvQuantity.getText().toString().equals("") ){
//            quantity = Integer.parseInt(tvQuantity.getText().toString());
//            Log.d("QUANTITY",String.valueOf(quantity));
//        }
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
                productOrdered productOrdered =new productOrdered(idProductOrdered,nameProductOrdered,salePrice,quantity,null);
                list.add(productOrdered);
                producctOrderAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });
    }


}