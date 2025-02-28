package com.example.newsapp.ui.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newsapp.R;
import com.example.newsapp.adapter.OnboardingAdapter;
import com.example.newsapp.ui.home.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private MaterialButton btnNext, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        List<OnboardingItem> items = new ArrayList<>();
        items.add(new OnboardingItem(R.drawable.ic_launcher_foreground, "Hoş Geldiniz!", "NewsApp ile en güncel haberlere ulaşın."));
        items.add(new OnboardingItem(R.drawable.ic_launcher_foreground, "Özelleştirilebilir", "İstediğiniz kategorileri takip edin."));
        items.add(new OnboardingItem(R.drawable.ic_launcher_foreground, "Bildirimler", "Size özel haber bildirimleri alın."));

        viewPager.setAdapter(new OnboardingAdapter(items));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        }).attach();

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < items.size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                completeOnboarding();
            }
        });

        btnSkip.setOnClickListener(v -> completeOnboarding());
    }

    private void completeOnboarding() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        prefs.edit().putBoolean("is_first_run", false).apply();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}