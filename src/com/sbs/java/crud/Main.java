package com.sbs.java.crud;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("명령어 )");
		String command = sc.nextLine();   // 한줄 통채로 받는다.
		
//		command = sc.next();            // abc abc 하면 abc만 입력받음 공백뒤 절삭
//		command = sc.next(); 2번 받기 위함.
		System.out.printf("입력된 명령어 : %s\n", command);
		
		System.out.printf("명령어 )");
		int num = sc.nextInt();
		System.out.printf("입력된 명령어 : %s\n", num);
		
		sc.close();
		
		
		
		
		System.out.println("== 프로그램 끝 ==");
	}
}
