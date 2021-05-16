package com.sbs.java.crud;

import java.util.Scanner;
import com.sbs.java.crud.controller.ArticleController;
import com.sbs.java.crud.controller.Controller;
import com.sbs.java.crud.controller.MemberController;

public class App {
 
	static void start() {
		
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.MakeTestData();
		memberController.MakeTestData();
		
		while(true) {
			System.out.printf("명령어 )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" 명령어를 입력하지 않았습니다. 입력해주세요.\n");
				continue;
			}
			
			if(command.equals("system exit")) {
				break;
			}
			
			String[] commandBits = command.split(" ");

			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;

			} else if (controllerName.equals("member")) {
				controller = memberController;
				
			} else {
				System.out.printf("존재하지 않는 명령어입니다.\n");
				continue;
			}
			
			controller.doAction(command, actionMethodName);
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== 프로그램 끝 ===");

	}
}

	




