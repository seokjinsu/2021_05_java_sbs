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
			System.out.println("�ش� ��ɾ�� �������� �ʽ��ϴ�. Ȯ���� �Է����ּ���.");
			break;
		}
	}
	
	public void doLogin() {
		System.out.printf("���̵� �Է��ϼ��� : ");
		String loginId = sc.nextLine();
		System.out.printf("��й�ȣ�� �Է��ϼ��� : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("�ش� ȸ���� �������� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է����ּ���.");
			return;
		}
		if (member.login_PW.equals(loginPw) == false) {
			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return;
		}
		loginedMember = member;

		System.out.printf("�α��� �Ǿ����ϴ�. ȯ���մϴٿ�!\n", loginedMember.name);
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
			System.out.printf("�α��� ���̵� : ");
			login_ID = sc.nextLine();
			
			if(isJoinableLoginId(login_ID) == false) {
				System.out.printf("%s��(��) �̹� ������� ���̵��Դϴ�.\n", login_ID);
				continue;
			}
			break;
		}
		
		System.out.printf("����� �̸� : ");
		String name = sc.nextLine();
		
		String login_PW;
		
		while(true) {
			System.out.printf("�α��� ��й�ȣ : ");
			login_PW = sc.nextLine();
			
			System.out.printf("��й�ȣ ���Է� : ");
			String login_PW_Retry = sc.nextLine();
			
			if (login_PW.equals(login_PW_Retry) == true) {
				break;
			}else {
				System.out.println("��й�ȣ�� �� �Է��ϼ���");
				continue;
			}
		}

		
		//data �κ�
		String time1;
		time1 = Util.getNowDateStr();
		
		
		Member member = new Member(id,time1,name,login_ID,login_PW);
		members.add(member);
		
		System.out.printf("%d�� ȸ���� ���� �Ǿ����ϴ�.\n", id);
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
