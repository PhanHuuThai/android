package com.example.coffeeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.Bill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomBill extends RecyclerView.Adapter<CustomBill.ViewHolder>{

    private ArrayList<Bill> BillList ;
    private Context context;

    public CustomBill(ArrayList<Bill> billList, Context ct) {
        BillList = billList;
        this.context = ct;
    }

    public CustomBill(ArrayList<Bill> billList) {
        BillList = billList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_item, parent, false);

        return new ViewHolder(view);
    }

    public void filterList(ArrayList<Bill> filterlist) {
        BillList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bill bill = BillList.get(position);
        holder.img_tien.setImageResource(R.drawable.icon_tien);
        holder.tv_billid.setText(bill.getId());
        holder.tv_gia.setText(Double.toString(bill.getPrice()));
        holder.tv_time.setText(bill.getTime());
        holder.btn_Xem.setImageResource(R.drawable.icon_kinhlup);
        holder.btn_Xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(context, BillDetailView.class);
                e.putExtra("idOrder", bill.getId());
                context.startActivity(e);
            }
        });

    }

    @Override
    public int getItemCount() {
        return BillList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_tien;
        public TextView tv_billid;
        public TextView tv_gia;
        public TextView tv_time;
        public FloatingActionButton btn_Xem;

        public ViewHolder(View view) {
            super(view);
            img_tien = (ImageView) view.findViewById(R.id.img_tien1);
            tv_billid = (TextView) view.findViewById(R.id.tv_billid);
            tv_gia = (TextView) view.findViewById(R.id.tv_gia);
            tv_time = (TextView) view.findViewById(R.id.tv_gio);
            btn_Xem = (FloatingActionButton) view.findViewById(R.id.btn_xem);

        }
    }
}
