package com.example.coffeeapp.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.employeeTimekeeping;

import java.util.List;

public class employeeTimekeepingAdapter  extends RecyclerView.Adapter<employeeTimekeepingAdapter.DataViewHolder>{
    List<Staff> employeeTimekeepings;
    //private Context context;
    public employeeTimekeepingAdapter(List<Staff> employeeTimekeepings) {
        this.employeeTimekeepings = employeeTimekeepings;
        // this.context = context;
    }
    @NonNull
    @Override
    public employeeTimekeepingAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item_timekeeping, parent, false);
        return new employeeTimekeepingAdapter.DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Staff employeeTKP= employeeTimekeepings.get(position);
        holder.tvName.setText(employeeTKP.getName());
    }



    @Override
    public int getItemCount() {
        if (employeeTimekeepings != null) {
            return employeeTimekeepings.size();
        }
        return 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName =  (TextView) itemView.findViewById(R.id.tv_nameEmployeeTKP);
        }
    }
}
