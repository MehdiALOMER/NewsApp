package com.example.newsapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.data.model.Article;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsAdapter newsAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView tanımlama
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Haberleri getir ve RecyclerView içinde göster
        newsViewModel.getEverything("bitcoin").observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if (articles != null && !articles.isEmpty()) {
                    // Adapter'ı oluştur ve RecyclerView'a bağla
                    newsAdapter = new NewsAdapter(MainActivity.this, articles);
                    recyclerView.setAdapter(newsAdapter);

                    // Haberler yüklendiğinde ProgressBar'ı gizle, RecyclerView'ı göster
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    Log.e(TAG, "Haber listesi boş!");
                }
            }
        });
    }
}