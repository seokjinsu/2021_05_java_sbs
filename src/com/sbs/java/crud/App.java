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
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
//		int lastArticle_id = 3;
//		List<Article> articles = new ArrayList<>();
		
		MakeTestData();
		
		while(true) {
			System.out.printf("��ɾ� )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" ��ɾ �Էµ��� �ʾҽ��ϴ�.\n �Է����ּ���");
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
			}else if(command.startsWith("articles list")){
				
				String serchkeyword = command.substring("articles list".length()).trim();
				
				List<Article> forListArticles = articles;
				
				if (articles.size() == 0) {
					System.out.println("�Խù��� �����ϴ�.");
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
						System.out.println("�˻������ �������� �ʽ��ϴ�.");
						continue;
					}
				}
				
				System.out.println("��ȣ / ����  /  ��ȸ��");

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
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				
				foundArticle.article_counterValue += 1;
				System.out.printf("\n��ȣ : %s\n",foundArticle.id);
				System.out.printf("��ȸ�� : %s\n",foundArticle.article_counterValue);
				System.out.printf("��¥ : %s\n",foundArticle.regDate);
				System.out.printf("���� : %s\n",foundArticle.title);
				System.out.printf("���� : %s\n\n",foundArticle.body);
				
				
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
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				
				
				articles.remove(found_Index);
				System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
				
			}else if(command.startsWith("article modify")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticlesById(id);
				
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
				foundArticle.regDate = time1;
				
				System.out.printf("%d�� �Խù��� ���� �Ǿ����ϴ�.\n\n", id);
				
			}else if(command.equals("member join")){
				int id = members.size() + 1;

				String login_ID = null;
				
				while (true) {
					System.out.printf("�α��� ���̵� : ");
					login_ID = sc.nextLine();
					
					if(isJoinableLoginId(login_ID) == false) {
						System.out.printf("%s��(��) �̹� ������� ���̵��Դϴ�.\n", login_ID);
						continue;
					}
					break;
				}
				
				System.out.printf("����� �̸� : ");
				String name = sc.nextLine();
				
				String login_PW;
				while(true) {
					System.out.printf("�α��� ��й�ȣ : ");
					login_PW = sc.nextLine();
					
					System.out.printf("��й�ȣ ���Է� : ");
					String login_PW_Retry = sc.nextLine();
					
					if (login_PW.equals(login_PW_Retry) == true) {
						break;
					}else {
						System.out.println("��й�ȣ�� �� �Է��ϼ���");
						continue;
					}
				}

				
				//data �κ�
				String time1;
				time1 = Util.getNowDateStr();
				
				
				Member member = new Member(id,time1,name,login_ID,login_PW);
				members.add(member);
				
				System.out.printf("%d�� ȸ���� ���� �Ǿ����ϴ�.\n", id);
				
			}else {
				System.out.printf("%s�� �������� �ʴ� ��ɾ��Դϴ�.\n",command);
			}
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== ���α׷� �� ===");

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
//		//	��ȸ
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
		
		articles.add(new Article(1,"����1","����1",Util.getNowDateStr(),16));
		articles.add(new Article(2,"����2","����2",Util.getNowDateStr(),31));
		articles.add(new Article(3,"����3","����3",Util.getNowDateStr(),51));
		
		
		System.out.println("�⺻ �׽�Ʈ �Խñ��� �����Ǿ����ϴ�.");
	}
		
}



