package com.example.islam.articles.data.models;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("name")
	private String name;

	@SerializedName("avatar")
	private String avatar;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"name = '" + name + '\'' + 
			",avatar = '" + avatar + '\'' + 
			"}";
		}
}