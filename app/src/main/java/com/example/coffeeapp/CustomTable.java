package com.example.coffeeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.bean.Table;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomTable extends RecyclerView.Adapter<CustomTable.ViewHolder> {

    private ArrayList<Table> TableList ;
//    private static ArrayList<Integer> statusban = new ArrayList<>();
    Context context;

    public CustomTable(ArrayList<Table> tableList, Context ct) {
        TableList = tableList;
        this.context = ct;
//        for(int i = 0; i < 5; i++){
//            statusban.add(1);
//        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item, parent, false);

        return new ViewHolder(view);
    }

    public void filterList(ArrayList<Table> filterlist) {
        TableList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int k = position;
        holder.img_table.setImageResource(R.drawable.table);
        holder.tv_ban.setText(TableList.get(k).getName());
        if(TableList.get(k).getStatus() == 0){
            holder.tv_trangthai.setText("Còn Chỗ");
        } else {
            holder.tv_trangthai.setText("Hết Chỗ");
        }

        holder.tv_status.setText("Trạng Thái");
//        if(statusban.get(k) == 1) {
            holder.img_status.setImageResource(R.drawable.icon_xanh);
//        } else {
//            holder.img_status.setImageResource(R.drawable.icon_do);
//        }


        holder.img_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = holder.img_status.getDrawable();
                Drawable iconXanhDrawable = context.getResources().getDrawable(R.drawable.icon_xanh); // Hoặc getDrawable(R.drawable.icon_xanh)
                boolean isIconXanh = drawable != null && drawable.getConstantState().equals(iconXanhDrawable.getConstantState());
                if(isIconXanh){
//                    statusban.set(k, 1);
                    holder.img_status.setImageResource(R.drawable.icon_do);
                } else {
//                    statusban.set(k, 0);
                    holder.img_status.setImageResource(R.drawable.icon_xanh);
                }
            }
        });

        holder.btn_Add.setImageResource(R.drawable.icon_cong);
        holder.btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = holder.img_status.getDrawable();
                Drawable iconXanhDrawable = context.getResources().getDrawable(R.drawable.icon_do); // Hoặc getDrawable(R.drawable.icon_xanh)
                boolean isIconDo = drawable != null && drawable.getConstantState().equals(iconXanhDrawable.getConstantState());

                if(isIconDo){
                    Toast.makeText(context.getApplicationContext(), "Bàn đã có người ngồi",Toast.LENGTH_SHORT).show();
                } else {
                    Intent e = new Intent(context, OrderActivity.class);
                    e.putExtra("nameTable", TableList.get(k).getName());
                    e.putExtra("idTable", TableList.get(k).getId());
                    context.startActivity(e);
                }

            }
        });

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
