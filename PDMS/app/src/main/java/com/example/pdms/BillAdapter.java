package com.example.pdms;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

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
        holder.bill.setText(fees.getBill());
        holder.id.setText(fees.getId());
        holder.level.setText(fees.getLevel());

        /* //may not need to delete bill by patient
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String BillId = fees.getId();
                FirebaseDatabase.getInstance().getReference("Bill")
                        .child(BillId).setValue(null);

                return true;
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return BillList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bill,level,id;
        public ImageView deleteItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bill = itemView.findViewById(R.id.Bill_view);
            id = itemView.findViewById(R.id.ID_view);
            level = itemView.findViewById(R.id.Level_view);
            deleteItem = itemView.findViewById(R.id.delete_Item);

        }
    }
}
