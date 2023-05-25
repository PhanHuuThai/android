package com.example.coffeeapp;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.account;
import com.example.coffeeapp.view.EmployeeTimekeepingV;
import com.example.coffeeapp.view.OrderByEmployee;
import com.example.coffeeapp.viewmodel.StaffApiService;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class registers extends AppCompatActivity {
    private StaffApiService apiService;
    private ImageButton btnaddstaff;
    private Button btnadd,btnback;
    private EditText editid,editname,editphone,editemail,editdayofbirth,editaddress,editsalary,editpass;
    private String url ="";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        editid = findViewById(R.id.editId);
        editname = findViewById(R.id.editName);
        editphone = findViewById(R.id.editPhoneNumber);
        editemail = findViewById(R.id.editEmail);
        editdayofbirth = findViewById(R.id.editDayofbirth);
        editaddress = findViewById(R.id.editAddress);
        editsalary = findViewById(R.id.editSalary);
        editpass = findViewById(R.id.editPass);

        btnaddstaff = findViewById(R.id.btn_AddStaff);
        btnaddstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1 );
            }
        });
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(registers.this, AllStaff.class);
                startActivity(intent);
            }
        });
        btnadd = findViewById(R.id.btn_ADD);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = new StaffApiService();
                List<Object> list= new ArrayList<>();

                Staff staff = new Staff(String.valueOf(editid.getText()),String.valueOf(editname.getText()),String.valueOf(editphone.getText()),String.valueOf(editemail.getText()),String.valueOf(editdayofbirth.getText()),String.valueOf(editaddress.getText()),url,Integer.parseInt(String.valueOf(editsalary.getText())),0);
                account Account = new account(String.valueOf(editid.getText()),String.valueOf(editpass.getText()), 1);
                list.add(staff);
                list.add(Account);
                apiService.addEmployee(list);

                Log.d("DEBUG","AAAAA" +list);

                Intent intent= new Intent(registers.this, AllStaff.class);
                startActivity(intent);
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
    private void uploadImage(Uri uri) {
        Map config = new HashMap();
        config.put("cloud_name", "dte2ps5qs");
        config.put("api_key", "791388445627371");
        config.put("api_secret", "rNQYUi2PS8ZdL8wKYExiUyZN9_4");
        try {
            MediaManager.init(this, config);
            String requestId = MediaManager.get()
                    .upload(uri)
                    .callback(new UploadCallback() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onStart(String requestId) {
                            Log.d(TAG, "onStart: " + "started");

                        }
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {
                            Log.d(TAG, "onStart: " + "uploading");
                        }

                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            Log.d(TAG, "onStart: " + "success");
                            String publicId = (String) resultData.get("public_id");
                            url = MediaManager.get().url().transformation(new Transformation().width(500).height(500).crop("fill")).format("jpg").secure(true).generate(publicId);
                            Log.d("IMAGE", url);
                            Picasso.get().load(url).resize(200, 200).into(btnaddstaff);
                        }

                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onError(String requestId, ErrorInfo error) {
                            Log.d(TAG, "onStart: " + error);
                        }

                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {
                            Log.d(TAG, "onStart: " + error);
                        }
                    }).dispatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}