package com.example.coffeeapp.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import  com.example.coffeeapp.model.foodItem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;

import java.util.List;

public class foodItemAdapter extends RecyclerView.Adapter<foodItemAdapter.DataViewHolder>{

    List<foodItem> foodItems;
    //private Context context;
    public foodItemAdapter(List<foodItem> food) {
        this.foodItems = food;
       // this.context = context;
    }

    @NonNull
    @Override
    public foodItemAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new DataViewHolder(view);
    }
    public void filterList(List<foodItem> foodItems) {
        foodItems = foodItems;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        foodItem foodItem = foodItems.get(position);
        holder.tvName.setText(foodItem.getNameFood());
        holder.tvQuantity.setText(String.valueOf(foodItem.getQuantity()));
        holder.tvTotal.setText(String.valueOf(foodItem.getTotalPrice()));
    }

    @Override
    public int getItemCount() {

        if (foodItems != null) {
            return foodItems.size();
        }
        return 0;
    }

    public  class DataViewHolder  extends RecyclerView.ViewHolder{
        private final TextView tvName;
        private final TextView tvTotal;
        private final TextView tvQuantity;
        private final Button btnRemove;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_nametable);
            this.tvTotal = (TextView) itemView.findViewById(R.id.tv_totalcostfood);
            this.tvQuantity =(TextView) itemView.findViewById(R.id.tv_Number);
            this.btnRemove = (Button) itemView.findViewById(R.id.btn_Remove);
        }
    }
}
