package com.example.newsapp.network;

import com.example.newsapp.data.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("everything")
    Call<NewsResponse> getEverything(@Query("q") String query, @Query("apiKey") String apiKey);

    class NewsResponse {
        public String status;
        public int totalResults;
        public List<Article> articles;
    }
}