package com.example.coffeeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.model.productOrdered;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    public MenuAdapter(ArrayList<productOrdered> contactList) {
        this.ContactList = contactList;
    }

        private ArrayList<productOrdered> ContactList ;
        @NonNull
        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drink_iteam, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position)
        {
            holder.tvname.setText(ContactList.get(position).getName());
            holder.tvprice.setText(String.valueOf(ContactList.get(position).getSalePrice()));
            holder.image.setImageResource(R.drawable.hampogar) ;
            holder.bt.setVisibility(View.GONE);
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
            bt.setVisibility(View.INVISIBLE);
        }


    }

}
