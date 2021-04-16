package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText RegName, RegEmail, RegPass;
    CheckBox DoctorBox, AdminBox;
    Button RegBTN;
    String isUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        RegEmail = findViewById(R.id.RegisterEmail);
        RegPass = findViewById(R.id.RegisterPassword);
        RegName = findViewById(R.id.RegisterName);

        DoctorBox = findViewById(R.id.RegisterDoctor);
        AdminBox = findViewById(R.id.RegisterAdmin);

        RegBTN = findViewById(R.id.RegisterBTN);

        RegBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = RegEmail.getText().toString();
                String Password = RegPass.getText().toString();
                String Name = RegName.getText().toString();

                isType();

                if(Email.isEmpty() || Password.isEmpty() || Name.isEmpty()){
                    Toast.makeText(Register.this, "Please fill in Email and Password", Toast.LENGTH_SHORT).show();
                }
                else if(Password.length()<6){
                    Toast.makeText(Register.this, "Password need to be 6 or more", Toast.LENGTH_SHORT).show();
                }
                else if(!Email.isEmpty() && !Password.isEmpty() && !Name.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                regUserType(isUser,Email,Name);
                            }
                            else {
                                Toast.makeText(Register.this, "Register UnSuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Register.this, "Error Occur", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void isType(){
        if(DoctorBox.isChecked()){
            isUser = "Doctors";
        }
        if(AdminBox.isChecked()){
            isUser = "Admins";
        }
        if(!AdminBox.isChecked() && !DoctorBox.isChecked()){
            isUser = "Patients";
        }
    };

    private void regUserType(String userType, String email, String name){//register the type of User
        UserType UserIs = new UserType(userType);//create a user type

        FirebaseDatabase.getInstance().getReference("UserType")//get the folder UserType from firebase
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())//make a child with the User ID
                .setValue(UserIs).addOnCompleteListener(new OnCompleteListener<Void>() {//set the value of the ID to user type
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    regUserData(email, name); //if the task is successful call regUserData to register email
                }
                else{//else the task is not successful make a toast about it
                    Toast.makeText(Register.this, "Register User Type not successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void regUserData(String email, String name){
        UserDB User = new UserDB(email,name);

        FirebaseDatabase.getInstance().getReference(isUser)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Register.this, "Register Data UnSuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}