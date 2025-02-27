package com.example.newsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.data.model.Article;
import com.example.newsapp.data.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private final NewsRepository newsRepository;

    public NewsViewModel() {
        newsRepository = new NewsRepository();
    }

    public LiveData<List<Article>> getEverything(String query) {
        return newsRepository.getEverything(query);
    }
}