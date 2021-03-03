package com.example.pdms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class AdminAccountSearch extends AppCompatActivity {
    EditText username, password;
    TextView textView;
    Admin_Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_search);
        username = (EditText)findViewById(R.id.username_input);
        password = (EditText)findViewById(R.id.password_input);
        textView = (TextView) findViewById(R.id.textView);
        controller = new Admin_Controller(this, "", null, 1);

    }
    public void btn_click(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                try {
                    controller.insert_users(username.getText().toString(), password.getText().toString());

                } catch (SQLiteException e) {
                    Toast.makeText(AdminAccountSearch.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btn_remove:
                controller.delete_users(username.getText().toString());
                break;
            case R.id.list_all_patients:
                controller.list_all_users(textView);
                break;
        }
    }
}