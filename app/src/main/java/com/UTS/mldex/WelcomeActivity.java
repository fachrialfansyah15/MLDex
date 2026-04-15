package com.UTS.mldex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.UTS.mldex.databinding.ActivityWelcomeBinding;

// WelcomeActivity.java
public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra("USERNAME");

        if (username != null) {
            binding.tvUsername.setText("Selamat Datang, " + username + "!");

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this, ListHero.class);
                startActivity(intent);
                finish();
            }, 2000);
        }
    }
}