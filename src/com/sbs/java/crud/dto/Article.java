package com.sbs.java.crud.dto;  //data transfer object

public class Article extends Dto{
	public String title;
	public String body;
	public int article_counterValue;
	
	public Article(int id, String title, String body, String date,int counter) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = date;
		this.article_counterValue = counter;
		
	}
	
	public void increaseCounter() {
		article_counterValue++;
	}
	
}