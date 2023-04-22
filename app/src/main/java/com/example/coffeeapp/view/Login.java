package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.MenuActivity;
import com.example.coffeeapp.R;
import com.example.coffeeapp.model.account;
import com.example.coffeeapp.viewmodel.LoginApiService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    LoginApiService apiLogin ;
    account accounter;
    EditText txtUserName;
    EditText txtPassWord;
    Button btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        txtUserName = findViewById(R.id.txtUserName);
        txtPassWord = findViewById(R.id.txtPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUserName.getText().toString();
                String pass = txtPassWord.getText().toString();
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pass)) {
                    // this method will call when email and password fields are empty.
                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản và mật khẩu !", Toast.LENGTH_SHORT).show();
                }
                Log.d("DEBUG",username);
                Log.d("DEBUG",pass);
                apiLogin = new LoginApiService();
                apiLogin.CheckLogin(username+"&"+pass).subscribeOn(Schedulers
                        .newThread()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<account>() {
                            @Override
                            public void onSuccess(@NonNull account account) {
                                accounter = account;
                                sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(EMAIL_KEY, accounter.getUserName());
                                editor.putString(PASSWORD_KEY, accounter.getPassWord());
                                editor.apply();
                                Log.d("DEBUG", accounter.toString());
                                if (accounter.getRole() == 0) {
                                    Intent e = new Intent(Login.this, AllStaff.class);
                                    startActivity(e);
                                } else {
                                    Intent e = new Intent(Login.this, MenuActivity.class);
                                    startActivity(e);
                                }
                            }
                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(Login.this, "Đăng nhập thất bại !", Toast.LENGTH_SHORT).show();                            }
                        });
            }
        });
    }
}
