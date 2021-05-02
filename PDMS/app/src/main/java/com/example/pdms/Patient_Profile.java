package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Patient_Profile extends AppCompatActivity {

     DatabaseReference databaseReference;
     AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private EditText EditName, EditDOB, EditRefBy, EditBlood, EditPhone,EditMedHist;
     Button EditSave, EditCancel;
     TextView Edit;
     TextView PatientNameV,PatientDOB,RefBy,BloodGroup,PhoneNum,PatientMedHist;
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);


        PatientNameV = findViewById(R.id.PatientNameV);
        PatientDOB = findViewById(R.id.PatientDOBV);
        RefBy = findViewById(R.id.PatientReferredByV);
        BloodGroup = findViewById(R.id.PatietnBloodV);
        PhoneNum = findViewById(R.id.PatientPhoneV);
        PatientMedHist = findViewById(R.id.PatientMedHistV);

        Edit = findViewById(R.id.PatientEditProfile);

        databaseReference = FirebaseDatabase.getInstance().getReference("PatientsProfile");
        ViewPatientProfile();

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPatientNewProfile();
            }
        });

    }

    public void EditPatientNewProfile(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View EditProfile = getLayoutInflater().inflate(R.layout.editpatientprofilepopup, null);
        EditName = (EditText) EditProfile.findViewById(R.id.EditPatientName);
        EditDOB = (EditText) EditProfile.findViewById(R.id.EditPatientDOB);
        EditRefBy = (EditText) EditProfile.findViewById(R.id.EditPatientRefBy);
        EditBlood = (EditText) EditProfile.findViewById(R.id.EditPatientBloodGroup);
        EditPhone = (EditText) EditProfile.findViewById(R.id.EditPatientPhone);
        EditMedHist = (EditText) EditProfile.findViewById(R.id.EditPatientMedHist);

        EditSave = (Button) EditProfile.findViewById(R.id.EditPatientSave);
        EditCancel = (Button) EditProfile.findViewById(R.id.EditPatientCancel);

        dialogBuilder.setView(EditProfile);
        alertDialog = dialogBuilder.create();
        alertDialog.show();

        EditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientsProfile newProfile = new PatientsProfile(EditName.getText().toString(),
                        EditDOB.getText().toString(),EditRefBy.getText().toString(),
                        EditBlood.getText().toString(), EditPhone.getText().toString(),
                        EditMedHist.getText().toString());

                FirebaseDatabase.getInstance().getReference("PatientsProfile")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(newProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            alertDialog.dismiss();
                        }
                        else{
                            Toast.makeText(Patient_Profile.this, "Save new profile unsuccessful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        EditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    void ViewPatientProfile()
    {
        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientsProfile profile = snapshot.getValue(PatientsProfile.class);
                if(profile != null){
                    String name = profile.getName();
                    String DOB = profile.getDOB();
                    String ReffBy = profile.getReferredBy();
                    String BloodG = profile.getBloodGroup();
                    String PhoneNumb = profile.getPhoneNum();
                    String MedHist = profile.getMedicalHistory();

                    PatientNameV.setText(name);
                    PatientDOB.setText(DOB);
                    RefBy.setText(ReffBy);
                    BloodGroup.setText(BloodG);
                    PhoneNum.setText(PhoneNumb);
                    PatientMedHist.setText(MedHist);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Patient_Profile.this, "Please set Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }


}