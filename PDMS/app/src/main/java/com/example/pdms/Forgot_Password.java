package com.example.pdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    EditText Reset_Email;
    Button ResetBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        Reset_Email = findViewById(R.id.Forgot_Password_Email);
        ResetBTN = findViewById(R.id.Forgot_Password_BTN);
        String Email = Reset_Email.getText().toString();

        ResetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.isEmpty()) {
                    Toast.makeText(Forgot_Password.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(Email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Forgot_Password.this, "Link have been to your Email", Toast.LENGTH_SHORT).show();
                                    Intent backToLogin = new Intent(Forgot_Password.this, Login.class);
                                    startActivity(backToLogin);
                                    finish();
                                } else {
                                    Toast.makeText(Forgot_Password.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
            }
        });
    }
}