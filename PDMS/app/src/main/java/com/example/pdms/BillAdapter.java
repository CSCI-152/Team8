package com.example.pdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {
    ArrayList<Fees> BillList;
    Context context;

    public BillAdapter(Context context, ArrayList<Fees> BillList){
        this.BillList = BillList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(context).inflate(R.layout.patient_bill_items, parent, false);
        return new MyViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Fees fees = BillList.get(position);
        holder.doctor.setText(fees.doctorname);
        holder.hospital.setText(fees.hopistal);
        holder.price.setText(fees.price);
    }

    @Override
    public int getItemCount() {
        return BillList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView doctor, hospital,price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doctor = itemView.findViewById(R.id.doctor_view);
            hospital = itemView.findViewById(R.id.hospital_view);
            price = itemView.findViewById(R.id.price_view);
        }
    }
}
