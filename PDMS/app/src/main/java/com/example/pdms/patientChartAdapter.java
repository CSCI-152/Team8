package com.example.pdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
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
        Reservation reserve = reservations.get(position);

        holder.reserveID.setText(reserve.getReservationID());
        holder.reserveDate.setText(reserve.getReservationDate());
        holder.reserveDate.setText(reserve.getReservationHM());
        holder.doctorID.setText(reserve.getDoctorID());
        holder.patientID.setText(reserve.getPatientID());
        holder.hospital.setText(reserve.getHospital());

        holder.Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reserveID = reserve.getReservationID();
                FirebaseDatabase.getInstance().getReference("Reservations")
                        .child(reserveID).setValue(null);

            }
        });

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static  class patientViewHolder extends RecyclerView.ViewHolder{

        TextView reserveID, reserveDate,reserveTime, doctorID, patientID, hospital;
        Button Accept, Decline;

        public patientViewHolder(@NonNull View itemView) {
            super(itemView);
            reserveID = itemView.findViewById(R.id.reserveID_view);
            reserveDate = itemView.findViewById(R.id.reserveDate_view);
            reserveTime = itemView.findViewById(R.id.reserveTime_view);
            doctorID = itemView.findViewById(R.id.doctorID_view);
            patientID = itemView.findViewById(R.id.patientID_view);
            hospital = itemView.findViewById(R.id.hosptial_view);

            Accept = itemView.findViewById(R.id.Accept_Reservation);
            Decline = itemView.findViewById(R.id.Decline_Reservation);

        }



    }
}
