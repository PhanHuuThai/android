package com.example.coffeeapp.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeapp.R;
import  com.example.coffeeapp.model.foodItem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class foodOrderedByEmployeeAdapter extends RecyclerView.Adapter<foodOrderedByEmployeeAdapter.ViewHolder>{
    List<foodItemAdapter> foodItems;

    public foodOrderedByEmployeeAdapter(List<foodItemAdapter> foodItems) {
        this.foodItems = foodItems;
    }
    @NonNull
    @Override
    public foodOrderedByEmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_by_employee, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull foodOrderedByEmployeeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rv= itemView.findViewById(R.id.viewEmployees);
        }
    }
}

