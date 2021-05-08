package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;

public class App {
	private static List<Article> articles;
	
	static {
		articles = new ArrayList<>();
	}
	
	static void start() {
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
//		int lastArticle_id = 3;
		
//		List<Article> articles = new ArrayList<>();
		
		MakeTestData();
		
		while(true) {
			System.out.printf("���ɾ� )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" ���ɾ �Էµ��� �ʾҽ��ϴ�.\n �Է����ּ���");
				continue;
			}
			
			if (command.equals("article write")) {

				int id = articles.size() + 1;
//				lastArticle_id = id;
				
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				//data �κ�
				String time1;
				time1 = Util.getNowDateStr();
				//ī����
				int counter = 0;
				
				Article article = new Article(id,title,body,time1,counter);
				articles.add(article);
				
				System.out.printf("%d�� ���� ���� �Ǿ����ϴ�.\n", id);
				
			}else if(command.equals("system exit")) {
				break;
			}else if(command.equals("articles list")){
				if (articles.size() == 0) {
					System.out.println("�Խù��� �����ϴ�.");
					continue;
				}
				System.out.println("��ȣ / ����  /  ��ȸ��");
				
				for(int i= articles.size() -1; i>=0; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d   / %s   /  %d \n",article.id, article.title, article.article_counterValue);
				}
			}else if(command.startsWith("article detail")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				
				
				for(int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}				
				
				foundArticle.article_counterValue += 1;
				System.out.printf("\n��ȣ : %s\n",id);
				System.out.printf("��ȸ�� : %s\n",foundArticle.article_counterValue);
				System.out.printf("��¥ : %s\n",foundArticle.regdate);
				System.out.printf("���� : %s\n",foundArticle.title);
				System.out.printf("���� : %s\n\n",foundArticle.body);
				
				
			}else if(command.startsWith("article delete")){
				String[] commandBits1 = command.split(" ");
				int id = Integer.parseInt(commandBits1[2]);
				
				int found_Index = -1;
				
				for(int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						found_Index = i;
						break;
					}
				}
				
				if(found_Index == -1) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				
				
				articles.remove(found_Index);
				System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
				
			}else if(command.startsWith("article modify")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				
				
				for(int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}			
				
				
				System.out.printf("�� ���� : ");
				String title = sc.nextLine();
				System.out.printf("�� ���� : ");
				String body = sc.nextLine();
				
				//data �κ�
				String time1;
				time1 = Util.getNowDateStr();
				
				
				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.regdate = time1;
				
				System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
				
			}else {
				System.out.printf("%s�� �������� �ʴ� ���ɾ��Դϴ�.",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== ���α׷� �� ===");

	}
	private static void MakeTestData() {
		
		articles.add(new Article(1,"����1","����1",Util.getNowDateStr(),16));
		articles.add(new Article(2,"����2","����2",Util.getNowDateStr(),31));
		articles.add(new Article(3,"����3","����3",Util.getNowDateStr(),51));
		
		
		System.out.println("�⺻ �׽�Ʈ �Խñ��� �����Ǿ����ϴ�.");
	}
		
}