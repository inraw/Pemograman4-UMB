package com.example.uts_pemrograman4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tampilkan welcome
        String email = getIntent().getStringExtra("USER_EMAIL");
        TextView tv = findViewById(R.id.tvWelcome);
        tv.setText("Welcome " + email);

        // tombol logout
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            // Buat intent ke LoginActivity dan clear task
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            // finish current activity
            finish();
        });
    }
}
