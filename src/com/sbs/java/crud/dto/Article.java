package com.sbs.java.crud.dto;  //data transfer object

public class Article extends Dto{
	public String title;
	public String body;
	public int article_counterValue;
	public int memberId;
	
//	public Article(int id, String title, String body, String date,int counter, int memberId) {
//		
//	}
	
	public Article(int id, String title, String body, String date,int counter, int memberId) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = date;
		this.article_counterValue = counter;
		this.memberId = memberId;
	}
	
	public void increaseCounter() {
		article_counterValue++;
	}
	
}