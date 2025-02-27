package com.example.newsapp.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.data.model.Article;
import com.example.newsapp.network.NewsApiService;
import com.example.newsapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsRepository {
    private static final String API_KEY = "6343a491d2dc471f954a238f0a1b3598";
    private final NewsApiService apiService;
    private static final String TAG = "NewsRepository";

    public NewsRepository() {
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(NewsApiService.class);
    }

    public LiveData<List<Article>> getEverything(String query) {
        MutableLiveData<List<Article>> newsData = new MutableLiveData<>();

        apiService.getEverything(query, API_KEY).enqueue(new Callback<NewsApiService.NewsResponse>() {
            @Override
            public void onResponse(Call<NewsApiService.NewsResponse> call, Response<NewsApiService.NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsData.setValue(response.body().articles);
                } else {
                    Log.e(TAG, "API Hatası: " + response.message() + " | Kod: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsApiService.NewsResponse> call, Throwable t) {
                Log.e(TAG, "Bağlantı Hatası: " + t.getMessage());
            }
        });

        return newsData;
    }
}