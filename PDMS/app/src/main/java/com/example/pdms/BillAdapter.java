package com.example.pdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {
    ArrayList<Fees> BillList;
    Context context;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void deleteItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    public BillAdapter(Context context, ArrayList<Fees> BillList){
        this.BillList = BillList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list = LayoutInflater.from(context).inflate(R.layout.patient_bill_items, parent, false);
        return new MyViewHolder(list, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Fees fees = BillList.get(position);
        holder.bill.setText(fees.getBill());
        holder.level.setText(fees.getLevel());
    }

    @Override
    public int getItemCount() {
        return BillList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bill,level;
        public ImageView deleteItem;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            bill = itemView.findViewById(R.id.Bill_view);
            level = itemView.findViewById(R.id.Level_view);
            deleteItem = itemView.findViewById(R.id.delete_Item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.deleteItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
