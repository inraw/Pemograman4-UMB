package com.example.uts_pemrograman4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private Button   btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper    = new DatabaseHelper(this);
        etEmail     = findViewById(R.id.etEmailReg);
        etPass      = findViewById(R.id.etPassReg);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass  = etPass.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Email tidak valid");
                return;
            }
            if (pass.length() < 6) {
                etPass.setError("Password minimal 6 karakter");
                return;
            }

            boolean inserted = dbHelper.insertUser(email, pass);
            if (inserted) {
                Toast.makeText(this, "Register berhasil, silakan login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tvToLogin).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
