package com.example.coffeeapp.viewmodel;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeeapp.model.productOrdered;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.view.OrderByEmployee;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProducctOrderAdapter extends RecyclerView.Adapter<ProducctOrderAdapter.DataViewHolder>{

    List<productOrdered> foodItems;
    private ProductOrderApiService apiService;



    public ProducctOrderAdapter(List<productOrdered> food) {
        this.foodItems = food;
    }

    @NonNull
    @Override
    public ProducctOrderAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new DataViewHolder(view);
    }
    public void filterList(List<productOrdered> foodItems) {
//        notifyDataSetChanged();
        foodItems = foodItems;
        notifyDataSetChanged();

    }
    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        productOrdered PBOItem = foodItems.get(position);
        holder.tvName.setText(PBOItem.getName());
        holder.tvQuantity.setText(String.valueOf(PBOItem.getQuantity()));
        holder.tvTotal.setText(String.valueOf(PBOItem.getQuantity()*PBOItem.getSalePrice()));
            Picasso.get()
            .load(PBOItem.getImage())
            .into(holder.imageView);

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
        private final ImageView imageView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_nametable);
            this.tvTotal = (TextView) itemView.findViewById(R.id.tv_totalcostfood);
            this.tvQuantity =(TextView) itemView.findViewById(R.id.tv_Number);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageFoodItem);

            this.btnRemove = (Button) itemView.findViewById(R.id.btn_Remove);
            this.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openFeedbackDialog(Gravity.CENTER,tvName.getText().toString(),imageView.getDrawable());
                }
            });
        }

        private void openFeedbackDialog(int gravity,String text,Drawable icon) {
            final Dialog dialog = new Dialog(btnRemove.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_remove_food);
            Window window = dialog.getWindow();
            if(window == null){
                return;
            }
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windownAttribute = window.getAttributes();
            windownAttribute.gravity = gravity;
            window.setAttributes(windownAttribute);
            CircleImageView CIV_food = dialog.findViewById(R.id.CIV_food);
            CIV_food.setBackground(icon);
            TextView tvName = dialog.findViewById(R.id.textView25);
            tvName.setText(text);
            Button btnCancel = dialog.findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            Button btnDel = dialog.findViewById(R.id.btn_del);
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    for (productOrdered item:foodItems
                         ) {
                        if(item.getName().equals(text)){
                            apiService = new ProductOrderApiService();
                            apiService.deletePBO(String.valueOf(item.getId()));
                            filterList(foodItems);
                             new OrderByEmployee().reload();
                            break;
                        }
                    }
                }
            });

//            if(Gravity.BOTTOM == gravity){
//                dialog.setCancelable(true);
//            }
//            else {
//                dialog.setCancelable(false);
//            }

            dialog.show();
        }
    }

}
