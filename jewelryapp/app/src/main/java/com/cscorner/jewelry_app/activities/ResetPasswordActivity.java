package com.cscorner.jewelry_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cscorner.jewelry_app.database.DBHelper;
import com.cscorner.jewelry_app.R;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etNewPassword, etConfirmPassword;
    Button btnReset;
    DBHelper db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_main);

        // Get email from intent
        email = getIntent().getStringExtra("email");

        // Initialize views
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnReset = findViewById(R.id.btnResetPassword);

        // Initialize DB
        db = new DBHelper(this);

        btnReset.setOnClickListener(v -> {
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if(newPass.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
            }
            else if(!newPass.equals(confirmPass)){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            else{
                boolean updated = db.updatePassword(email, newPass);
                if(updated){
                    Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Go back to login screen
                } else {
                    Toast.makeText(this, "Error updating password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
