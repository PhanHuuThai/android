package com.example.coffeeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private AlertDialog.Builder dialogbuildder ;
    private AlertDialog dialog ;
    private Context m_context ;
    private View v;
    private TextInputEditText txtsize , txtsoluong,txtchuthich ;
    private Button bt_huy, bt_them,btorder ;
    public ProductAdapter(ArrayList<Product> contactList, Context context , View view)
    {
        this.v=view ;
        this.m_context=context ;
        this.ContactList = contactList;
        this.dialogbuildder= new AlertDialog.Builder(m_context) ;
        dialogbuildder.setView(v) ;
        dialog=dialogbuildder.create();
        bt_huy = (Button) v.findViewById(R.id.popup_cancle) ;
        bt_huy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
    }
    public ProductAdapter(ArrayList<Product> contactList)
    {
        this.ContactList = contactList;
    }

        private ArrayList<Product> ContactList ;
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
            holder.tvname.setText(ContactList.get(position).getName());
            holder.tvprice.setText(String.valueOf(ContactList.get(position).getPrice()));
            holder.image.setImageResource(R.drawable.hampogar) ;
            holder.bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog.show();
                }
            });
        }
        public void Addproduct()
        {
            dialogbuildder= new AlertDialog.Builder(m_context) ;
            View AddproductPopup = v ;
//        txtsize = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtsize) ;
//        txtsoluong = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtsoluong) ;
//        txtchuthich = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtchuthich) ;

            dialogbuildder.setView(AddproductPopup) ;
            dialog=dialogbuildder.create();
            dialog.show();
            bt_huy = (Button) AddproductPopup.findViewById(R.id.popup_cancle) ;
            bt_huy.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });
        }

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
