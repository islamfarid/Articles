package com.example.islam.articles.data.models;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem{

	@SerializedName("datePublished")
	private String datePublished;

	@SerializedName("image")
	private String image;

	@SerializedName("author")
	private Author author;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setDatePublished(String datePublished){
		this.datePublished = datePublished;
	}

	public String getDatePublished(){
		return datePublished;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesItem{" + 
			"datePublished = '" + datePublished + '\'' + 
			",image = '" + image + '\'' + 
			",author = '" + author + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}