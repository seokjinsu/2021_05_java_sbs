package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Member;
import com.sbs.java.crud.util.Util;

public class MemberController extends Controller{
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;
	private Member loginedMember;
	
	public MemberController(Scanner sc) {
		this.sc = sc;

		members = new ArrayList<Member>();
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		
		default:
			System.out.println("해당 명령어는 존재하지 않습니다. 확인후 입력해주세요.");
			break;
		}
	}
	
	public void doLogin() {
		System.out.printf("아이디를 입력하세요 : ");
		String loginId = sc.nextLine();
		System.out.printf("비밀번호를 입력하세요 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다. 확인 후 다시 입력해주세요.");
			return;
		}
		if (member.login_PW.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		loginedMember = member;

		System.out.printf("로그인 되었습니다. 환영합니다요!\n", loginedMember.name);
	}
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByloginID(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	
	public void doJoin() {
		int id = members.size() + 1;

		String login_ID = null;
		
		while (true) {
			System.out.printf("로그인 아이디 : ");
			login_ID = sc.nextLine();
			
			if(isJoinableLoginId(login_ID) == false) {
				System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", login_ID);
				continue;
			}
			break;
		}
		
		System.out.printf("사용자 이름 : ");
		String name = sc.nextLine();
		
		String login_PW;
		
		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			login_PW = sc.nextLine();
			
			System.out.printf("비밀번호 재입력 : ");
			String login_PW_Retry = sc.nextLine();
			
			if (login_PW.equals(login_PW_Retry) == true) {
				break;
			}else {
				System.out.println("비밀번호를 재 입력하세요");
				continue;
			}
		}

		
		//data 부분
		String time1;
		time1 = Util.getNowDateStr();
		
		
		Member member = new Member(id,time1,name,login_ID,login_PW);
		members.add(member);
		
		System.out.printf("%d번 회원이 생성 되었습니다.\n", id);
	}
	
	private boolean isJoinableLoginId(String login_ID) {
		int index = getMemberIndexByloginID(login_ID);
		
		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByloginID(String login_ID) {
		int i = 0;
		
		for(Member member: members) {
			if(member.login_ID.equals(login_ID)) {
				return i;
			}
			i ++;
			
		}
		return -1;
	}
}
