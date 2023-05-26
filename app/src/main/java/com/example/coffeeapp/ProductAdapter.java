package com.example.coffeeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.view.RevenueView;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private AlertDialog.Builder dialogbuildder ;
    private AlertDialog dialog ;
    private Context m_context ;
    private View v;
    private ArrayList<productOrdered> ContactList ;
    private TextInputEditText txtsize , txtsoluong,txtchuthich ;
    private static ArrayList<productOrdered> productsss =new ArrayList<>();
    private static ArrayList<String> chuthich = new ArrayList<>();
    private Button bt_huy, bt_them,btorder ;
    private TextInputEditText edSoluong, edChuThich;
    private int h;
    public ProductAdapter(ArrayList<productOrdered> contactList, Context context , View view)
    {
        this.v=view ;
        this.m_context=context ;
        this.ContactList = contactList;
        this.dialogbuildder= new AlertDialog.Builder(m_context) ;
        dialogbuildder.setView(v) ;
        dialog=dialogbuildder.create();
        edSoluong = v.findViewById(R.id.edTextSoluong);
        edChuThich = v.findViewById(R.id.ed_chuthich);
        //Log.d("listcontact", ContactList.get(1).getName());
        bt_huy = (Button) v.findViewById(R.id.popup_cancle) ;
        bt_huy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        bt_them = v.findViewById(R.id.popup_btadd);
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContactList!=null)
                {
                    if(String.valueOf(edSoluong.getText()).equals("")){
                        Toast.makeText(context.getApplicationContext(), "Bạn cần nhập số lượng",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        productOrdered product = new productOrdered(ContactList.get(h).getId(), ContactList.get(h).getName(),
                                ContactList.get(h).getSalePrice(), Integer.parseInt(String.valueOf(edSoluong.getText())), ContactList.get(h).getImage(), ContactList.get(h).getIdCategory());
                        chuthich.add(String.valueOf(edChuThich.getText()));


                        Log.d("chuthich", chuthich.toString());
                        productsss.add(product);
                        dialog.dismiss();
                    }

                }

            }
        });


    }


    public ProductAdapter(ArrayList<productOrdered> contactList)
    {
        this.ContactList = contactList;
    }
    public ProductAdapter()
    {

    }

    public ArrayList<productOrdered> getProductsss(){
        return productsss;
    }

    public void setProductsss(){
        productsss.clear();
    }

    public ArrayList<String> getChuthich(){
        return chuthich;
    }

    public void setChuthich(){
        chuthich.clear();
    }


        @NonNull
        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drink_iteam, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position)
        {
            int n = position;
            holder.tvname.setText(ContactList.get(position).getName());
            holder.tvprice.setText(String.valueOf(ContactList.get(position).getSalePrice()));
            Picasso.get().load(ContactList.get(position).getImage()).into(holder.image);
            Log.d("IMG1",ContactList.get(position).getImage()) ;
            String x=ContactList.get(position).getImage() ;
            Log.d("IMG",x) ;
            holder.bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View x1)
                {
                    h = n;
                    ImageView image1;
                    image1 = (ImageView) v.findViewById(R.id.ImagePopup);
                    Picasso.get().load(x).into(image1);
                    dialog.show();
                }
            });
        }
//        public void Addproduct()
//        {
//            dialogbuildder= new AlertDialog.Builder(m_context) ;
//            View AddproductPopup = v ;
//
//            dialogbuildder.setView(AddproductPopup) ;
//            dialog=dialogbuildder.create();
//            dialog.show();
//            bt_huy = (Button) AddproductPopup.findViewById(R.id.popup_cancle) ;
//            bt_huy.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    dialog.dismiss();
//                }
//            });
//        }

        @Override
        public int getItemCount() {
        return ContactList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname;
        public TextView tvprice;
        public ImageView image;
        public Button bt;

        public ViewHolder(View view) {
            super(view);
            tvname = (TextView) view.findViewById(R.id.txtTenMon);
            tvprice = (TextView) view.findViewById(R.id.txtgiathanh);
            image = (ImageView) view.findViewById(R.id.imageproduct);
            bt=(Button) view.findViewById(R.id.bt_add_drinkiteam) ;
        }


    }

}
