package com.example.pdms;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class patientDosageAdapter extends RecyclerView.Adapter<patientDosageAdapter.patientViewHolder> {

    ArrayList<Prescription> prescriptions;
    Context context;

    public patientDosageAdapter(Context context, ArrayList<Prescription> prescriptions){
        this.context = context;
        this.prescriptions = prescriptions;
    }


    @NonNull
    @Override
    public patientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patient = LayoutInflater.from(context).inflate(R.layout.dosage_items, parent, false);

        return new patientViewHolder(patient);
    }


    @Override
    public void onBindViewHolder(@NonNull patientViewHolder holder, int position) {
       Prescription prescription = prescriptions.get(position);
        holder.tvdoctorname.setText(prescription.getDoctorname());
        holder.tvdoctoraddress.setText(prescription.getDoctoraddress());
        holder.tvpatientname.setText(prescription.getPatientname());
        holder.tvpatientphonenumber.setText(prescription.getPhonenumber());
        holder.tvpatientaddress.setText(prescription.getAddress());
        holder.tvpatientage.setText(prescription.getAge());
        holder.tvpatientgender.setText(prescription.getGender());
        holder.tvpatientprescription.setText(prescription.getMedication());
        holder.tvdoctorsignature.setText(prescription.getSignature());
        holder.tvdate.setText(prescription.getDate());
    }

    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    public static  class patientViewHolder extends RecyclerView.ViewHolder{

        private TextView tvdate, tvdoctoraddress, tvdoctorname, tvpatientphonenumber,
                tvpatientaddress, tvpatientage, tvpatientgender, tvpatientprescription, tvdoctorsignature,
              tvpatientname;


        public patientViewHolder(@NonNull View itemView) {
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
