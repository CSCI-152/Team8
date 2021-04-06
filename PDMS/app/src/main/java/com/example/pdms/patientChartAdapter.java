package com.example.pdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class patientChartAdapter extends RecyclerView.Adapter<patientChartAdapter.patientViewHolder> {

    ArrayList<Reservation> reservations;
    Context context;

    public patientChartAdapter(Context context, ArrayList<Reservation> reservations){
        this.context = context;
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public patientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patient = LayoutInflater.from(context).inflate(R.layout.reservation_items, parent, false);

        return new patientViewHolder(patient);
    }

    @Override
    public void onBindViewHolder(@NonNull patientViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        //holder.reserveTime.setText(reservation.getReservationTime().toString());
        holder.reserveID.setText(reservation.getReservationID());
        holder.doctorID.setText(reservation.getDoctorID());
        holder.patientID.setText(reservation.getPatientID());
        holder.hospital.setText(reservation.getHospital());

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static  class patientViewHolder extends RecyclerView.ViewHolder{

        TextView reserveID, reserveTime, doctorID, patientID, hospital;

        public patientViewHolder(@NonNull View itemView) {
            super(itemView);
            reserveID = itemView.findViewById(R.id.reserveID_view);
            //reserveTime = itemView.findViewById(R.id.reserveDate_view);
            doctorID = itemView.findViewById(R.id.doctorID_view);
            patientID = itemView.findViewById(R.id.patientID_view);
            hospital = itemView.findViewById(R.id.hosptial_view);
        }
    }
}
