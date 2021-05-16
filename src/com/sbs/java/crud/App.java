package com.sbs.java.crud;

import java.util.Scanner;
import com.sbs.java.crud.controller.ArticleController;
import com.sbs.java.crud.controller.Controller;
import com.sbs.java.crud.controller.MemberController;

public class App {
 
	static void start() {
		
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.MakeTestData();
		memberController.MakeTestData();
		
		while(true) {
			System.out.printf("��ɾ� )");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0) {
				System.out.println(" ��ɾ �Է����� �ʾҽ��ϴ�. �Է����ּ���.\n");
				continue;
			}
			
			if(command.equals("system exit")) {
				break;
			}
			
			String[] commandBits = command.split(" ");

			if (commandBits.length == 1) {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
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
				System.out.printf("�������� �ʴ� ��ɾ��Դϴ�.\n");
				continue;
			}
			
			controller.doAction(command, actionMethodName);
				
				
		}
		
		sc.close();
	
		
		System.out.println("=== ���α׷� �� ===");

	}
}

	




