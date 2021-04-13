package com.example.pdms;


import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrescriptionAdapter extends ArrayAdapter {
    private Activity context;
    private List<Prescription> prescriptionList;

    public PrescriptionAdapter(Activity context, List<Prescription> prescriptionList) {
        super(context, R.layout.layout_prescription, prescriptionList);
        this.context = context;
        this.prescriptionList = prescriptionList;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.layout_prescription, null, true);
        TextView tvdoctoraddress = listItemView.findViewById(R.id.tvdoctoraddress);
        TextView tvdoctorname = listItemView.findViewById(R.id.tvdoctorname);
        TextView tvpatientname = listItemView.findViewById(R.id.tvpatientname);
        TextView tvpatientphonenumber = listItemView.findViewById(R.id.tvpatientphonenumber);
        TextView tvpatientaddress = listItemView.findViewById(R.id.tvpatientaddress);
        TextView tvpatientage = listItemView.findViewById(R.id.tvpatientage);
        TextView tvpatientgender = listItemView.findViewById(R.id.tvpatientgender);
        TextView tvpatientprescription = listItemView.findViewById(R.id.tvpatientprescription);
        TextView tvdoctorsignature = listItemView.findViewById(R.id.tvdoctorsignature);
        TextView tvdate = listItemView.findViewById(R.id.tvdate);
        Prescription item = prescriptionList.get(position);
        tvdoctoraddress.setText(item.getDoctoraddress());
        tvdoctorname.setText(item.getDoctorname());
        tvpatientname.setText(item.getPatientname());
        tvpatientphonenumber.setText(item.getPhonenumber());
        tvpatientaddress.setText(item.getAddress());
        tvpatientage.setText(item.getAge());
        tvpatientgender.setText(item.getGender());
        tvpatientprescription.setText(item.getMedication());
        tvdoctorsignature.setText(item.getSignature());
        tvdate.setText(item.getDate());
        return listItemView;
    }


    public void onBindViewHolder(@NonNull PrescriptionAdapter.ViewHolder holder, int position) {
        final Prescription item = prescriptionList.get(position);
        holder.tvdoctoraddress.setText(item.getDoctoraddress());
        holder.tvdoctorname.setText(item.getDoctorname());
        holder.tvpatientname.setText(item.getPatientname());
        holder.tvpatientphonenumber.setText(item.getPhonenumber());
        holder.tvpatientaddress.setText(item.getAddress());
        holder.tvpatientage.setText(item.getAge());
        holder.tvpatientgender.setText(item.getGender());
        holder.tvpatientprescription.setText(item.getMedication());
        holder.tvdoctorsignature.setText(item.getSignature());
        holder.tvdate.setText(item.getDate());

    }

    public int getItemCount() {
        return prescriptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvdoctoraddress, tvdoctorname, tvpatientname, tvpatientphonenumber,
                tvpatientaddress, tvpatientage, tvpatientgender, tvpatientprescription, tvdoctorsignature,
                tvdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdoctoraddress = itemView.findViewById(R.id.tvdoctoraddress);
            tvdoctorname = itemView.findViewById(R.id.tvdoctorname);
            tvpatientname = itemView.findViewById(R.id.tvpatientname);
            tvpatientphonenumber = itemView.findViewById(R.id.tvpatientphonenumber);
            tvpatientaddress = itemView.findViewById(R.id.tvpatientaddress);
            tvpatientage = itemView.findViewById(R.id.tvpatientage);
            tvpatientgender = itemView.findViewById(R.id.tvpatientgender);
            tvpatientprescription = itemView.findViewById(R.id.tvpatientprescription);
            tvdoctorsignature = itemView.findViewById(R.id.tvdoctorsignature);
            tvdate = itemView.findViewById(R.id.tvdate);


        }
    }
}
