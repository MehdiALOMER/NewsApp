package com.example.newsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newsapp.R;
import com.example.newsapp.ui.onboarding.OnboardingActivity;
import com.example.newsapp.ui.home.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME = 2000; // 2 saniye splash süresi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
            boolean isFirstRun = prefs.getBoolean("is_first_run", true);

            if (isFirstRun) {
                startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            finish(); // SplashActivity'yi kapat
        }, SPLASH_TIME);
    }
}
