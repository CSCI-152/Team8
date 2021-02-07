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
    EditText RegEmail, RegPass, RegKey;
    CheckBox DoctorBox, AdminBox;
    Button RegBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        RegEmail = findViewById(R.id.RegisterEmail);
        RegPass = findViewById(R.id.RegisterPassword);
        RegKey = findViewById(R.id.RegisterKey);

        DoctorBox = findViewById(R.id.RegisterDoctor);
        AdminBox = findViewById(R.id.RegisterAdmin);

        RegBTN = findViewById(R.id.RegisterBTN);

        RegBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = RegEmail.getText().toString();
                String Password = RegPass.getText().toString();
                String Key = RegKey.getText().toString();

                Boolean Doctor = DoctorBox.getText().equals(true);
                Boolean Admin = AdminBox.getText().equals(true);

                String UserType;
                if(isAdmin(Admin,Key)){//if isAdmin is true set UserType to Admin
                    UserType = "Admin";
                }
                else if(isDoctor(Doctor,Key)){//if isDoctor is true set UserType to Doctor
                    UserType = "Doctor";
                }
                else{//else is not Admin and Doctor set UserType to Patient
                    UserType = "Patient";
                }

                if(Email.isEmpty() || Password.isEmpty()){
                    Toast.makeText(Register.this, "Please fill in Email and Password", Toast.LENGTH_SHORT).show();
                }
                else if(Password.length()<6){
                    Toast.makeText(Register.this, "Password need to be 6 or more", Toast.LENGTH_SHORT).show();
                }
                else if(!Email.isEmpty() && !Password.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                UserDB User = new UserDB(UserType, Email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                            Intent goToLogin = new Intent(Register.this, Login.class);
                                            startActivity(goToLogin);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(Register.this, "Register Data UnSuccessful", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
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

    private boolean isDoctor(Boolean Doctor, String KEY){
        if(Doctor && (KEY=="1")){//if doctor is true and the key is 1 than return true
            return true;
        }
        else{
            return false;
        }
    };

    private boolean isAdmin(Boolean Admin, String KEY){
        if(Admin && (KEY=="2")){//if admin is true and the key is 2 than return true
            return true;
        }
        else{
            return false;
        }
    };

}