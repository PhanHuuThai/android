package com.example.coffeeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomTable extends RecyclerView.Adapter<CustomTable.ViewHolder> {

    private ArrayList<String> TableList ;

    public CustomTable(ArrayList<String> tableList) {
        TableList = tableList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);

        return new ViewHolder(view);
    }

    public void filterList(ArrayList<String> filterlist) {
        TableList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int k = position;
        holder.img_table.setImageResource(R.drawable.table);
        holder.tv_ban.setText("Bàn " + Integer.toString(k));
        holder.tv_trangthai.setText(TableList.get(k));
        holder.tv_status.setText("Trạng Thái");
        if(holder.tv_trangthai.getText().equals("Còn Chỗ")){
            holder.img_status.setImageResource(R.drawable.icon_xanh);
        } else {
            holder.img_status.setImageResource(R.drawable.icon_do);
        }
        holder.btn_Add.setImageResource(R.drawable.icon_cong);
    }

    @Override
    public int getItemCount() {
        return TableList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_table;
        public TextView tv_ban;
        public TextView tv_trangthai;
        public TextView tv_status;
        public ImageView img_status;
        public FloatingActionButton btn_Add;

        public ViewHolder(View view) {
            super(view);
            img_table = (ImageView) view.findViewById(R.id.img_ban);
            tv_ban = (TextView) view.findViewById(R.id.tv_tenban);
            tv_trangthai = (TextView) view.findViewById(R.id.tv_trangthai);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            img_status = (ImageView) view.findViewById(R.id.img_status);
            btn_Add = (FloatingActionButton) view.findViewById(R.id.btn_add);

        }


    }
}
