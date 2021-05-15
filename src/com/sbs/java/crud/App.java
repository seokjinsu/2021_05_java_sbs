package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.dto.Member;
import com.sbs.java.crud.util.Util;

public class App {
	private static List<Article> articles;
	private static List<Member> members;
	
	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	static void start() {
		
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
//		int lastArticle_id = 3;
//		List<Article> articles = new ArrayList<>();
		
		MakeTestData();
		
		while(true) {
			System.out.printf("명령어 )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" 명령어를 입력되지 않았습니다.\n 입력해주세요");
				continue;
			}
			
			if (command.equals("article write")) {

				int id = articles.size() + 1;
//				lastArticle_id = id;
				
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
			}else if(command.startsWith("articles list")){
				
				String serchkeyword = command.substring("articles list".length()).trim();
				
				List<Article> forListArticles = articles;
				
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				
				if (serchkeyword.length() > 0) {
					forListArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if(article.title.contains(serchkeyword)) {
							forListArticles.add(article);
						}
					}
					
					if (forListArticles.size() == 0) {
						System.out.println("검색결과가 존재하지 않습니다.");
						continue;
					}
				}
				
				System.out.println("번호 / 제목  /  조회수");

				for (int i = forListArticles.size() - 1; i >= 0; i--) {
					Article article = forListArticles.get(i);

					System.out.printf("%d   / %s   /  %d \n", article.id, article.title, article.article_counterValue);
				}
				
			}else if(command.startsWith("article detail")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
//				Article foundArticle = null;
				
				Article foundArticle = getArticlesById(id);
				
//				for(int i = 0; i < articles.size();i++) {
//					Article article = articles.get(i);
//					
//					if (article.id == id) {
//						foundArticle = article;
//						break;
//					}
//				}
//				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.article_counterValue += 1;
				System.out.printf("\n번호 : %s\n",foundArticle.id);
				System.out.printf("조회수 : %s\n",foundArticle.article_counterValue);
				System.out.printf("날짜 : %s\n",foundArticle.regDate);
				System.out.printf("제목 : %s\n",foundArticle.title);
				System.out.printf("내용 : %s\n\n",foundArticle.body);
				
				
			}else if(command.startsWith("article delete")){
				String[] commandBits1 = command.split(" ");
				int id = Integer.parseInt(commandBits1[2]);
				
//				int found_Index = -1;
				int found_Index = getArticleIndexById(id);
				
//				for(int i = 0; i < articles.size();i++) {
//					Article article = articles.get(i);
//					
//					if (article.id == id) {
//						found_Index = i;
//						break;
//					}
//				}
				
				if(found_Index == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				
				articles.remove(found_Index);
				System.out.printf("%d번 게시물이 삭제 되었습니다.\n\n", id);
				
			}else if(command.startsWith("article modify")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticlesById(id);
				
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
				foundArticle.regDate = time1;
				
				System.out.printf("%d번 게시물이 변경 되었습니다.\n\n", id);
				
			}else if(command.equals("member join")){
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
				
			}else {
				System.out.printf("%s는 존재하지 않는 명령어입니다.\n",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== 프로그램 끝 ===");

	}
	
	private static boolean isJoinableLoginId(String login_ID) {
		int index = getMemberIndexByloginID(login_ID);
		
		if (index == -1) {
			return true;
		}
		return false;
	}

	private static int getMemberIndexByloginID(String login_ID) {
		int i = 0;
		
		for(Member member: members) {
			if(member.login_ID.equals(login_ID)) {
				return i;
			}
			i ++;
			
		}
		return -1;
	}
	
	private static int getArticleIndexById(int id) {
		int i = 0;
		
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			
			}
			i++;
		}

		return -1;
	}
	
	private static Article getArticlesById(int id) {

		int index = getArticleIndexById(id);
		
		if (index != -1) {
			return articles.get(index);
		}
			
//		for (Article article : articles) {
//			
//			if(article.id == id) {
//				return article;
//			}
//		}
			
		return null;
	}
	
//	private static Article getArticlesById(int id) {
//		//	순회
//		for (int i=0;i<articles.size(); i++) {
//			Article article = articles.get(i);
//			
//			if( article.id == id) {
//				return article;
//			}
//		}
//			
//		return null;
//	}
	
	private static void MakeTestData() {
		
		articles.add(new Article(1,"제목1","내용1",Util.getNowDateStr(),16));
		articles.add(new Article(2,"제목2","내용2",Util.getNowDateStr(),31));
		articles.add(new Article(3,"제목3","내용3",Util.getNowDateStr(),51));
		
		
		System.out.println("기본 테스트 게시글이 생성되었습니다.");
	}
		
}



