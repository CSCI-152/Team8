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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText et_email, et_password;
    Button bt_login;
    TextView txt_forgot, txt_register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);
        txt_forgot = findViewById(R.id.txt_forgot);
        txt_register = findViewById(R.id.txt_register);
        auth = FirebaseAuth.getInstance();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(et_email) || !isEmpty(et_password)) {
                    Toast.makeText(Login.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    userLogin(et_email.getText().toString(), et_password.getText().toString());
                }
            }
        });
        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Forgot Test!!", Toast.LENGTH_SHORT).show();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Register Test!!", Toast.LENGTH_SHORT).show();
                Intent goToRegister = new Intent(Login.this, Register.class);
                startActivity(goToRegister);
            }
        });
    }
    private boolean isEmpty(EditText text) {
        return text.getText().toString().trim().length() == 0;
    }

    private void userLogin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                try {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        //finish();
                    } else {
                        Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(Login.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}