package com.sbs.java.crud.dto;  //data transfer object

public class Article{
	public String title;
	public String body;
	public int id;
	public String regdate;
	public int article_counterValue;
	
	public Article(int id, String title, String body, String date,int counter) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regdate = date;
		this.article_counterValue = counter;
		
	}

	
}