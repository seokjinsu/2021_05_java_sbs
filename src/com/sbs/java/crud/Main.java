package com.sbs.java.crud;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
		int lastArticle_id = 0;
		
		while(true) {
			System.out.println("��ɾ� )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" ��ɾ �Էµ��� �ʾҽ��ϴ�.\n �Է����ּ���");
				continue;
			}
			
			if (command.equals("articles write")) {
				int id = lastArticle_id+1;
				lastArticle_id = id;
				
				System.out.println("���� : ");
				String title = sc.nextLine();
				System.out.println("���� : ");
				String body = sc.nextLine();
				
				System.out.printf("%d�� ���� ���� �Ǿ����ϴ�.\n", id);
				
			}else if(command.equals("system exit")) {
				break;
			}else if(command.equals("Articles list")){
				System.out.println("�Խù��� �����ϴ�.");
			}else {
				System.out.printf("%s�� �������� �ʴ� ��ɾ��Դϴ�.",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== ���α׷� �� ===");

	}
}
