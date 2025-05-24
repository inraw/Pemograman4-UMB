package com.example.uts_pemrograman4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private Button   btnLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper   = new DatabaseHelper(this);
        etEmail    = findViewById(R.id.etEmailLogin);
        etPass     = findViewById(R.id.etPassLogin);
        btnLogin   = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass  = etPass.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Email tidak valid");
                return;
            }
            if (pass.isEmpty()) {
                etPass.setError("Password harus diisi");
                return;
            }

            boolean ok = dbHelper.checkUser(email, pass);
            if (ok) {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("USER_EMAIL", email);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Login gagal: email atau password salah", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tvToRegister).setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }
}
