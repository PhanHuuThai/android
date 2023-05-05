package com.example.coffeeapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.personal_information;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    private List<Staff> StaffList;
    Context context;
    public StaffAdapter(List<Staff> staffList,Context context)
    {
        StaffList= staffList;
        this.context = context;
    }
    @NonNull
    @Override
    public StaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_item, parent, false);

        return new StaffAdapter.ViewHolder(view);
    }

    public void filterList(List<Staff> filterlist) {
        StaffList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.ViewHolder holder, int position) {

        Staff staff = StaffList.get(position);
        holder.img_staff.setImageResource(R.drawable.icon_nhanvien);
        holder.tv_name.setText(staff.getName());
        holder.tv_salary.setText(String.valueOf(staff.getSalary()));
        holder.btn_Xem.setImageResource(R.drawable.icon_kinhlup);
        holder.btn_Xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG","StaffAdapter");
                Intent e = new Intent(context, personal_information.class);
                e.putExtra("id",staff.getId());
                e.putExtra("Fullname",staff.getName());
                e.putExtra("Phonenumber",staff.getPhonenumber());
                e.putExtra("email",staff.getEmail());
                e.putExtra("Dayofbirth",staff.getDayofbirth());
                e.putExtra("Address",staff.getAddress());
                e.putExtra("Username",staff.getImageEmployee());
                e.putExtra("Salary",staff.getSalary());
                e.putExtra("Numberworkingday",staff.getWorkHour());
                Log.d("DEBUG","StaffAdapter_1");
                context.startActivity(e);

            }
        });
    }

    @Override
    public int getItemCount() {
        return StaffList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_staff;
        public TextView tv_name;
        public TextView tv_salary;
        public FloatingActionButton btn_Xem;

        public ViewHolder(View view) {
            super(view);
            img_staff = (ImageView) view.findViewById(R.id.img_staff);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_salary = (TextView) view.findViewById(R.id.tv_salary);
            btn_Xem = (FloatingActionButton) view.findViewById(R.id.btn_xem);

        }


    }


}
