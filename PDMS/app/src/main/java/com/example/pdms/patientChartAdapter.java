package com.example.pdms;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        holder.reserveTime.setText(reserve.getReservationHM());
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

        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View AcceptView = LayoutInflater.from(v.getContext())
                        .inflate(R.layout.patientchart_bill_popup, null);
                EditText setBill, setLevel;
                Button acceptBill, cancelBill;
                setBill = AcceptView.findViewById(R.id.bill_patientChart_bill_View);
                setLevel = AcceptView.findViewById(R.id.level_patientChart_Bill_View);

                acceptBill = AcceptView.findViewById(R.id.Accept_PatientChart_Bill_btn);
                cancelBill = AcceptView.findViewById(R.id.Cancel_PatientChart_Bill_btn);

                builder.setView(AcceptView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                acceptBill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fees fees = new Fees(setBill.getText().toString(), reserve.getReservationID(),
                                setLevel.getText().toString(), reserve.getPatientID());
                        String reserveID = reserve.getReservationID();
                        FirebaseDatabase.getInstance().getReference("Bill")
                                .child(reserveID).setValue(fees).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    FirebaseDatabase.getInstance()
                                            .getReference("DoctorAppointment")
                                            .child(reserveID).setValue(reserve);
                                    FirebaseDatabase.getInstance().getReference("Reservations")
                                            .child(reserveID).setValue(null);
                                    alertDialog.dismiss();
                                }

                            }
                        });
                    }
                });

                cancelBill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
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
