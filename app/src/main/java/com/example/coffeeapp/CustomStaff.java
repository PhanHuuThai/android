package com.example.coffeeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.Been.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomStaff extends RecyclerView.Adapter<CustomStaff.ViewHolder> {
    private ArrayList<Staff> StaffList;
    Context context;
    public CustomStaff(ArrayList<Staff> staffList, Context context)
    {
        StaffList= staffList;
        this.context = context;
    }
    @NonNull
    @Override
    public CustomStaff.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_item, parent, false);

        return new CustomStaff.ViewHolder(view);
    }

    public void filterList(ArrayList<Staff> filterlist) {
        StaffList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomStaff.ViewHolder holder, int position) {

        Staff staff = StaffList.get(position);
        holder.img_staff.setImageResource(R.drawable.icon_nhanvien);
        holder.tv_name.setText(staff.getName());
        holder.tv_salary.setText(String.valueOf(staff.getSalary()));
        holder.btn_Xem.setImageResource(R.drawable.icon_kinhlup);
        holder.btn_Xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(context, personal_information.class);
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
