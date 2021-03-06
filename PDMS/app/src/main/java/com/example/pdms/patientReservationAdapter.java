package com.example.pdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class patientReservationAdapter extends RecyclerView.Adapter<patientReservationAdapter.patientReservationViewHolder> {

    ArrayList<Reservation> reservations;
    Context context;

    public patientReservationAdapter(Context context, ArrayList<Reservation> reservations){
        this.context = context;
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public patientReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patient = LayoutInflater.from(context).inflate(R.layout.patientreservationlist_items, parent, false);

        return new patientReservationViewHolder(patient);
    }

    @Override
    public void onBindViewHolder(@NonNull patientReservationViewHolder holder, int position) {
        Reservation reserve = reservations.get(position);

        holder.reserveID.setText(reserve.getReservationID());
        holder.reserveDate.setText(reserve.getReservationDate());
        holder.reserveTime.setText(reserve.getReservationHM());
        holder.doctorID.setText(reserve.getDoctorID());
        holder.patientID.setText(reserve.getPatientID());
        holder.hospital.setText(reserve.getHospital());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static  class patientReservationViewHolder extends RecyclerView.ViewHolder{

        TextView reserveID, reserveDate,reserveTime, doctorID, patientID, hospital;

        public patientReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            reserveID = itemView.findViewById(R.id.Patient_reserveID_view);
            reserveDate = itemView.findViewById(R.id.Patient_reserveDate_view);
            reserveTime = itemView.findViewById(R.id.Patient_reserveTime_view);
            doctorID = itemView.findViewById(R.id.Patient_doctorID_view);
            patientID = itemView.findViewById(R.id.Patient_patientID_view);
            hospital = itemView.findViewById(R.id.Patient_hosptial_view);


        }


    }
}
