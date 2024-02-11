package com.example.newsapp.data;


import java.util.List;

public class News {

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<Articles> getArticles() {
            return articles;
        }

        public void setArticles(List<Articles> articles) {
            this.articles = articles;
        }

        int totalResults;
        List<Articles> articles;

    }

