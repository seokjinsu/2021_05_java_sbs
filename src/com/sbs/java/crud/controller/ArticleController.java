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
				System.out.println("�۾���� �α����� �ؾ��մϴ�.");
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
			System.out.println("�ش� ��ɾ�� �������� �ʽ��ϴ�. Ȯ���� �Է����ּ���.");
			break;
		}
	}

	
	public void doWrite() {
			
		int id = articles.size() + 1;
//		lastArticle_id = id;
		
		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();
		
		//data �κ�
		String time1;
		time1 = Util.getNowDateStr();
		//ī����
		int counter = 0;
		
		Article article = new Article(id,title,body,time1,counter,loginedMember.id);
		articles.add(article);
		
		System.out.printf("%d�� ���� ���� �Ǿ����ϴ�.\n", id);
	}
	

	public void showList() {
		
		if (articles.size() == 0) {
			System.out.println("�Խù��� �����ϴ�.");
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
				System.out.println("�˻������ �������� �ʽ��ϴ�.");
				return;
			}
		}
		
		
		System.out.println(" ��ȣ | ����  | �ۼ��� | ��ȸ��");

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
			System.out.println("��ɾ Ȯ�� �� ���Է����ּ���.");
			return;
		}
		
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		
		foundArticle.increaseCounter();
		
		System.out.printf("\n��ȣ : %s\n",foundArticle.id);
		System.out.printf("��ȸ�� : %s\n",foundArticle.article_counterValue);
		System.out.printf("��¥ : %s\n",foundArticle.regDate);
		System.out.printf("�ۼ��� : %s\n",foundArticle.memberId);
		System.out.printf("���� : %s\n",foundArticle.title);
		System.out.printf("���� : %s\n\n",foundArticle.body);

	}

	public void doDelete() {
		int id =  Integer.parseInt(commandVaildation());
		
		if (id == -1){
			System.out.println("��ɾ Ȯ�� �� ���Է����ּ���.");
			return;
		}

//		int found_Index = getArticleIndexById(id);
//		
//		if(found_Index == -1) {
//			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
//			return;
//		}
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("�ϴ� ������ �����!!");
			return;
		}	
		
		articles.remove(foundArticle);
		System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
		
	}

	public void doModify() {
		int id =  Integer.parseInt(commandVaildation());
		
		if (id == -1){
			System.out.println("��ɾ Ȯ�� �� ���Է����ּ���.");
			return;
		}
		
		Article foundArticle = getArticlesById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("�ϴ� ������ �����!!");
			return;
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
		foundArticle.regDate = time1;
		
		System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
		
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
		
		articles.add(new Article(1,"����1","����1",Util.getNowDateStr(),16,1));
		articles.add(new Article(2,"����2","����2",Util.getNowDateStr(),31,2));
		articles.add(new Article(3,"����3","����3",Util.getNowDateStr(),51,3));
		
		
		System.out.println("�⺻ �׽�Ʈ �Խñ��� �����Ǿ����ϴ�.");
	}
		
}
	

