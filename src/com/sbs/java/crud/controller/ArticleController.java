package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;


public class ArticleController extends Controller{
	private Scanner sc;
	private List<Article> articles;
	private String command;
	private String actionMethodName;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		articles = new ArrayList<Article>();
	}
	
	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			if(islogined() == false) {
				System.out.println("글쓰기는 로그인을 해야합니다.");
				return;
			}
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("해당 명령어는 존재하지 않습니다. 확인후 입력해주세요.");
			break;
		}
	}

	
	public void doWrite() {
			
		int id = articles.size() + 1;
//		lastArticle_id = id;
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		//data 부분
		String time1;
		time1 = Util.getNowDateStr();
		//카운터
		int counter = 0;
		
		Article article = new Article(id,title,body,time1,counter,loginedMember.id);
		articles.add(article);
		
		System.out.printf("%d번 글이 생성 되었습니다.\n", id);
	}
	

	public void showList() {
		
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}
		
		String serchkeyword = command.substring("article list".length()).trim();
		
		List<Article> forListArticles = articles;
		
		if (serchkeyword.length() > 0) {
			forListArticles = new ArrayList<>();
			
			for (Article article : articles) {
				if(article.title.contains(serchkeyword)) {
					forListArticles.add(article);
				}
			}
			
			if (forListArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}
		}
		
		
		System.out.println(" 번호 | 제목  | 작성자 | 조회수");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("%4d | %4s | %4s | %4d \n", article.id, article.title, article.memberId ,article.article_counterValue);
		}
		
		
	}

	public void showDetail() {
//		String[] commandBits = command.split(" ");
//		int id = Integer.parseInt(commandBits[2]);
		
		int id =  Integer.parseInt(commandVaildation());
		
		if (id == -1){
			System.out.println("명령어를 확인 후 재입력해주세요.");
			return;
		}
		
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		foundArticle.increaseCounter();
		
		System.out.printf("\n번호 : %s\n",foundArticle.id);
		System.out.printf("조회수 : %s\n",foundArticle.article_counterValue);
		System.out.printf("날짜 : %s\n",foundArticle.regDate);
		System.out.printf("작성자 : %s\n",foundArticle.memberId);
		System.out.printf("제목 : %s\n",foundArticle.title);
		System.out.printf("내용 : %s\n\n",foundArticle.body);

	}

	public void doDelete() {
		int id =  Integer.parseInt(commandVaildation());
		
		if (id == -1){
			System.out.println("명령어를 확인 후 재입력해주세요.");
			return;
		}

//		int found_Index = getArticleIndexById(id);
//		
//		if(found_Index == -1) {
//			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
//			return;
//		}
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("니는 권한이 없어요!!");
			return;
		}	
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제 되었습니다.\n\n", id);
		
	}

	public void doModify() {
		int id =  Integer.parseInt(commandVaildation());
		
		if (id == -1){
			System.out.println("명령어를 확인 후 재입력해주세요.");
			return;
		}
		
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("니는 권한이 없어요!!");
			return;
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
		
	}
	
	//------------------
	
	private String commandVaildation() {
		String[] commandBits = command.split(" ");
		
		if (commandBits.length == 3){
			return commandBits[2];
		}
		return "-1";
	}
	
	private int getArticleIndexById(int id) {
		int i = 0;
		
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			
			}
			i++;
		}

		return -1;
	}
	
	private Article getArticlesById(int id) {

		int index = getArticleIndexById(id);
		
		if (index != -1) {
			return articles.get(index);
		}
			
		return null;
	}
	
	public void MakeTestData() {
		
		articles.add(new Article(1,"제목1","내용1",Util.getNowDateStr(),16,1));
		articles.add(new Article(2,"제목2","내용2",Util.getNowDateStr(),31,2));
		articles.add(new Article(3,"제목3","내용3",Util.getNowDateStr(),51,3));
		
		
		System.out.println("기본 테스트 게시글이 생성되었습니다.");
	}
		
}
	

