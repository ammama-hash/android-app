package com.cscorner.jewelry_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cscorner.jewelry_app.database.DBHelper;
import com.cscorner.jewelry_app.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText etEmail;
    Button btnNext;
    DBHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_main);

        // Initialize views
        etEmail = findViewById(R.id.etEmail);
        btnNext = findViewById(R.id.btnNext);

        // Initialize database
        db = new DBHelper(this);

        btnNext.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if(email.isEmpty()){
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            }
            else if(!db.checkUserEmail(email)){
                Toast.makeText(this, "Email not registered!", Toast.LENGTH_SHORT).show();
            }
            else{
                // Email exists, go to Reset Password Activity
                Intent intent = new Intent(ForgetPasswordActivity.this, com.cscorner.jewelry_app.activities.ResetPasswordActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
