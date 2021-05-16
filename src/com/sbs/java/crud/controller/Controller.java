package com.sbs.java.crud.controller;

import com.sbs.java.crud.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	
	public abstract void doAction(String command, String actionMethodName);
//	public abstract void maketestData();
	
	public static boolean islogined() {
		return loginedMember != null;
	}
}
