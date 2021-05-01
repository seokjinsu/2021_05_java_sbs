package com.sbs.java.crud;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		int lastArticle_id = 0;
		
		List<Article> articles = new ArrayList<>();
		
		
		while(true) {
			System.out.println("명령어 )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" 명령어를 입력되지 않았습니다.\n 입력해주세요");
				continue;
			}
			
			if (command.equals("articles write")) {

				int id = lastArticle_id+1;
				lastArticle_id = id;
				
				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id,title,body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성 되었습니다.\n", id);
				
			}else if(command.equals("system exit")) {
				break;
			}else if(command.equals("articles list")){
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");
				
				for(int i= articles.size() -1; i>=0; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d   / %s \n",article.id, article.title);
				}
			}else {
				System.out.printf("%s는 존재하지 않는 명령어입니다.",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== 프로그램 끝 ===");

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