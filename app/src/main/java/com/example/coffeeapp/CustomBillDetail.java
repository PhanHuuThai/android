package com.example.coffeeapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.model.productOrdered;

import java.util.ArrayList;

public class CustomBillDetail extends RecyclerView.Adapter<CustomBillDetail.ViewHolder>{
    private ArrayList<BillDetail> BillDetailList ;
    private ArrayList<productOrdered> productList;
    private int value = 0;

    public CustomBillDetail(ArrayList<BillDetail> billDetailList, ArrayList<productOrdered> ProductList) {
        BillDetailList = billDetailList;
        productList = ProductList;
        Log.d("thai1", BillDetailList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_detail_item, parent, false);

        return new ViewHolder(view);
    }

    public void filterList(ArrayList<BillDetail> filterlist) {
        BillDetailList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position < BillDetailList.size()){

            BillDetail billDetail = BillDetailList.get(position);
            productOrdered product = productList.get(position);

            holder.tv_name.setText(product.getName());
            holder.tv_SL.setText(Integer.toString(billDetail.getQuantity()));
            holder.tv_thanhtien.setText((Integer.toString(billDetail.getQuantity()*(int)product.getSalePrice()))+"đ");
            value += billDetail.getQuantity()*(int)product.getSalePrice();
        } else {
            holder.tv_name.setText("Tổng tiền");
            holder.tv_SL.setText("");
            holder.tv_thanhtien.setText((Integer.toString(value)+"đ"));
        }

    }

    @Override
    public int getItemCount() {
        return BillDetailList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public TextView tv_size;
        public TextView tv_SL;
        public TextView tv_thanhtien;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_tenmon);
            tv_SL = (TextView) view.findViewById(R.id.tv_soluong);
            tv_thanhtien = (TextView) view.findViewById(R.id.tv_thanhtien);
        }


    }
}
