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
			System.out.printf("명령어 )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" 명령어를 입력되지 않았습니다.\n 입력해주세요");
				continue;
			}
			
			if (command.equals("article write")) {

				int id = lastArticle_id+1;
				lastArticle_id = id;
				
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				//data 부분
				String time1;
				time1 = Util.getNowDateStr();
				//카운터
				int counter = 0;
				
				Article article = new Article(id,title,body,time1,counter);
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
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}				
				
				foundArticle.article_counterValue += 1;
				System.out.printf("\n번호 : %s\n",id);
				System.out.printf("조회수 : %s\n",foundArticle.article_counterValue);
				System.out.printf("날짜 : %s\n",foundArticle.regdate);
				System.out.printf("제목 : %s\n",foundArticle.title);
				System.out.printf("내용 : %s\n\n",foundArticle.body);
				
				
				
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
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				
				articles.remove(found_Index);
				System.out.printf("%d번 게시물이 삭제 되었습니다.\n\n", id);
				
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
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}			
				
				
				System.out.printf("새 제목 : ");
				String title = sc.nextLine();
				System.out.printf("새 내용 : ");
				String body = sc.nextLine();
				
				//data 부분
				String time1;
				time1 = Util.getNowDateStr();
				
				
				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.regdate = time1;
				
				System.out.printf("%d번 게시물이 변경 되었습니다.\n\n", id);
				
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
	String regdate;
	int article_counterValue;
	
	public Article(int id, String title, String body, String date,int counter) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regdate = date;
		this.article_counterValue = counter;
		
	}

	
}