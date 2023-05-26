package com.example.coffeeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity
{
    private RecyclerView rv_product ;
    private ArrayList<productOrdered> productsList ;
    private ProductAdapter productAdapter ;
    private AlertDialog.Builder dialogbuildder ;
    private ProductOrderApiService productService;
    private AlertDialog dialog ;
    private TextInputEditText txtsize , txtsoluong,txtchuthich ;
    private Button bt_huy ,btorder ;
    private TextView tvTenBan;
    private static final int REQUEST_CODE = 1;
    private String nameTable, idTable;
    ArrayList<productOrdered> listOldProduct = new ArrayList<>();
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        int check = 0;

        listOldProduct = new ArrayList<>();
        Intent e = getIntent();
        nameTable = e.getStringExtra("nameTable");
        idTable = e.getStringExtra("idTable");

        Log.d("sss", nameTable);
        Log.d("check", String.valueOf(check));

        productService = new ProductOrderApiService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tvTenBan = findViewById(R.id.txtTenBan);
        tvTenBan.setText(nameTable);

        bt_huy = findViewById(R.id.bt_cancle);
        bt_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(OrderActivity.this, AllTable.class);

                startActivity(e);
            }
        });

        btorder = findViewById(R.id.but_GioHang);
        btorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductAdapter a = new ProductAdapter();
                ArrayList<productOrdered> productss = new ArrayList<>();

                productss = a.getProductsss();

                ArrayList<String> chuthich = a.getChuthich();
                Log.d("Chuthich1", chuthich.toString());

                Log.d("productss", productss.toString());

                Intent t = new Intent(OrderActivity.this, ListDrink.class);
                t.putExtra("nameTable", nameTable);
                t.putExtra("idTable", idTable);
                t.putExtra("list", productss);
                t.putExtra("list2", chuthich);

                startActivityForResult(t, REQUEST_CODE);
            }
        });


        productsList= new ArrayList<>() ;
        final  View AddproductPopup = getLayoutInflater().inflate(R.layout.addproduct_popup,null) ;

        productAdapter= new ProductAdapter(productsList,this,AddproductPopup) ;
        rv_product= findViewById(R.id.rv_product) ;
        rv_product.setLayoutManager(( new GridLayoutManager(this,2)));
        rv_product.setAdapter(productAdapter);

        productService.GetAllPBO("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                    @Override
                    public void onSuccess(@NonNull List<productOrdered> productOrdereds) {
                        for(productOrdered item : productOrdereds){
                            Log.d("DEBUB","TABLESUCCESS!");
                            productsList.add(item);
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Xử lý kết quả ở đây
                // Bạn có thể truy cập dữ liệu được trả về bằng cách sử dụng tham số `data`

                listOldProduct = (ArrayList<productOrdered>) data.getSerializableExtra("productList");

                check = data.getIntExtra("check", 0);
                Log.d("listOldProduct", listOldProduct.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // Xử lý trường hợp người dùng hủy thao tác
            }
        }
    }
}