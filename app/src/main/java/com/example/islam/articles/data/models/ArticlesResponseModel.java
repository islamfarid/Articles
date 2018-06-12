package com.example.islam.articles.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ArticlesResponseModel{

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	public void setArticles(List<ArticlesItem> articles){
		this.articles = articles;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesResponseModel{" + 
			"articles = '" + articles + '\'' + 
			"}";
		}
}