package com.sbs.java.crud;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
		int lastArticle_id = 0;
		
		List<Article> articles = new ArrayList<>();
		
		
		while(true) {
			System.out.printf("��ɾ� )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" ��ɾ �Էµ��� �ʾҽ��ϴ�.\n �Է����ּ���");
				continue;
			}
			
			if (command.equals("articles write")) {

				int id = lastArticle_id+1;
				lastArticle_id = id;
				
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(id,title,body);
				articles.add(article);
				
				System.out.printf("%d�� ���� ���� �Ǿ����ϴ�.\n", id);
				
			}else if(command.equals("system exit")) {
				break;
			}else if(command.equals("articles list")){
				if (articles.size() == 0) {
					System.out.println("�Խù��� �����ϴ�.");
					continue;
				}
				System.out.println("��ȣ / ����");
				
				for(int i= articles.size() -1; i>=0; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d   / %s \n",article.id, article.title);
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

				SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
				Date time = new Date();
				String time1 = format1.format(time);
				
				
				System.out.printf("\n��ȣ : %s\n",id);
				System.out.printf("��¥ : %s\n",time1);
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
				
			}else {
				System.out.printf("%s�� �������� �ʴ� ��ɾ��Դϴ�.",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== ���α׷� �� ===");

	}
}

class Article{
	String title;
	String body;
	int id;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		
	}

	
}