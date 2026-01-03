package com.cscorner.jewelry_app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cscorner.jewelry_app.database.DBHelper;
import com.cscorner.jewelry_app.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvSignupLink, tvForgetPassword;
    DBHelper db;

    public LoginActivity() {
        tvSignupLink = findViewById(R.id.tvSignupLink);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnlogin);
        tvForgetPassword = findViewById(R.id.forgotPassword);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean valid = db.checkUser(email, password);

            if(valid){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                // ✅ EMAIL SESSION PASSING
                Intent i = new Intent(LoginActivity.this, HomeScreenMain.class);
                i.putExtra("email", email);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignupLink.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        tvForgetPassword.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class)));
    }
}

