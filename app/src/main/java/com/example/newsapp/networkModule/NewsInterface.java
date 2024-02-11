package com.example.newsapp.networkModule;

import com.example.newsapp.data.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
    //https://newsapi.org/v2/everything?q=apple&from=2021-09-23&to=2021-09-23&sortBy=popularity&apiKey=API_KEY

    String API_KEY = "9c7ffe3c66d2955c0a5a3336dd"; //Need to replace with your API Key
    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getHeadlines(@Query(value = "country") String country);

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getBusinessHeadlines(@Query(value = "country") String country, @Query(value = "category") String category);

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getTechHeadlines(@Query(value = "sources") String sources);

    @GET(value = "v2/everything?apiKey=" + API_KEY)
    Call<News> getSportsHeadlines(@Query(value = "domains") String domains);

}
