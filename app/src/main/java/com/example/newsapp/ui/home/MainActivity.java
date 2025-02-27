package com.example.newsapp.ui.home;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.R;
import com.example.newsapp.data.model.Article;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NewsViewModel newsViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // "bitcoin" anahtar kelimesiyle haberleri getir
        newsViewModel.getEverything("bitcoin").observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if (articles != null && !articles.isEmpty()) {
                    for (Article article : articles) {
                        Log.d(TAG, "Haber: " + article.getTitle());
                    }
                } else {
                    Log.e(TAG, "Haber listesi boş!");
                }
            }
        });

    }
}