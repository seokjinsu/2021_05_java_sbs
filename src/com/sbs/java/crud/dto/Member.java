package com.sbs.java.crud.dto;

public class Member extends Dto{
	public String login_ID;
	public String login_PW;
	public String name;
	
	public Member(int id, String regdate, String name, String login_ID,String login_PW) {
		this.id = id;
		this.regDate = regdate;
		this.name = name;
		this.login_ID = login_ID;
		this.login_PW = login_PW;
		
	}

	
}
