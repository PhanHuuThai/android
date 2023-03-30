package com.example.coffeeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.BillDetail;

import java.util.ArrayList;

public class CustomBillDetail extends RecyclerView.Adapter<CustomBillDetail.ViewHolder>{
    private ArrayList<BillDetail> BillDetailList ;
    private int value = 0;

    public CustomBillDetail(ArrayList<BillDetail> billDetailList) {
        BillDetailList = billDetailList;
    }

    @NonNull
    @Override
    public CustomBillDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_detail_item, parent, false);

        return new CustomBillDetail.ViewHolder(view);
    }

    public void filterList(ArrayList<BillDetail> filterlist) {
        BillDetailList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBillDetail.ViewHolder holder, int position) {

        if(position < BillDetailList.size()){

            BillDetail billDetail = BillDetailList.get(position);
            holder.tv_name.setText(billDetail.getName());
            holder.tv_size.setText(billDetail.getSize());
            holder.tv_SL.setText(Integer.toString(billDetail.getQuantity()));
            holder.tv_thanhtien.setText((Integer.toString(billDetail.getQuantity()*billDetail.getPrice()))+"đ");
            value += billDetail.getQuantity()*billDetail.getPrice();
        } else {
            holder.tv_name.setText("Tổng tiền");
            holder.tv_size.setText("");
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
            tv_size = (TextView) view.findViewById(R.id.tv_size);
            tv_SL = (TextView) view.findViewById(R.id.tv_soluong);
            tv_thanhtien = (TextView) view.findViewById(R.id.tv_thanhtien);
        }


    }
}
