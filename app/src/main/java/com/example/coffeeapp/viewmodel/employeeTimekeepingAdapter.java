package com.example.coffeeapp.viewmodel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.employeeTimekeeping;

import java.util.ArrayList;
import java.util.List;

public class employeeTimekeepingAdapter  extends RecyclerView.Adapter<employeeTimekeepingAdapter.DataViewHolder>{
    List<Staff> employeeTimekeepings;
    //private Context context;
    public CheckBox checkbox;

    static List<Staff> timeKeeping = new ArrayList<>();

    public employeeTimekeepingAdapter(List<Staff> employeeTimekeepings) {
        this.employeeTimekeepings = employeeTimekeepings;
    }
    public  static List<Staff> timeKeepingg(){
        return timeKeeping;
    }
    public  static boolean resetList(){
        return timeKeeping.removeAll(timeKeeping);
    }
    @NonNull
    @Override
    public employeeTimekeepingAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item_timekeeping, parent, false);

        checkbox = view.findViewById(R.id.checkBox);

        return new employeeTimekeepingAdapter.DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Staff employeeTKP= employeeTimekeepings.get(position);
        holder.tvName.setText(employeeTKP.getName());
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    timeKeeping.add(employeeTKP);
                    Log.d("CHECKED","OK");
                    checkbox.setChecked(false);
                }
                if(!isChecked){
                    Log.d("CHECKED","NOT OK");
                }
            }
        });
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
        private CheckBox checkbox;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName =  (TextView) itemView.findViewById(R.id.tv_nameEmployeeTKP);
            this.checkbox= (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
