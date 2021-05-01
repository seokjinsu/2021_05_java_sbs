package com.sbs.java.crud;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
		int lastArticle_id = 0;
		
		List<Article> articles = new ArrayList<>();
		
		
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