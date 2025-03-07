package com.kh.mvc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * MemberView 클래스는 JDBC 실습을 위해 생성하였으며,
 * 사용자에게 입력 및 출력을 수행하는 메서드를 제공합니다.
 * 
 * @author : 종로 C강의장
 * @version : 0.1
 * @date : 2025-03-04
 */


public class UserView {

	private Scanner sc = new Scanner(System.in);
	private UserController userController = new UserController();
	
	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력해주는 메소드입니다. 
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("--- USER테이블 관리 프로그램 ---");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("3. 회원 비밀번호 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 회원 번호로 단일 회원 정보 조회");
			System.out.println("6. 회원 아이디 검색으로 회원 정보 조회");
			System.out.println("9. 프로그램 종료");
			System.out.print("이용할 메뉴를 선택해주세요 > ");
			
			int menuNo = 0;
			
			try {
				menuNo = sc.nextInt();
			} catch (InputMismatchException e) {
				
				sc.nextLine();
				continue;
			} 			
			sc.nextLine();
			
			
			switch (menuNo) {
			case 1 : findAll(); break;
			case 2 : insertUser(); break;
			case 3 : pwUpdate(); break;
			case 4 : userDelete(); break;
			case 5 : numFind(); break;
			case 6 : idFind(); break;
			case 9 : System.out.println("프로그램 종료~"); return;
			default : System.out.println("잘못된 메뉴 선택입니다");
			
			}
		}
	}
	
	
	
	/* 1. 회원 전체 정보를 조회해주는 기능 -> 반드시 mainMenu()를 이용해서만 사용 */
	// -> private으로 작성
	private void findAll() {
		
		System.out.println("\n--- 회원 전체 목록입니당 ---");
		
		// Controller야 쩌어기 DB가서 회원 전체 목록 좀 가져와줘
		List<UserDTO> list = userController.findAll();
		// -> 참조자료형 크기는 항상 4byte == 주소를 담기 때문에
		
		System.out.println("\n조회된 총 회원의 수는 " + list.size() + "명 입니다.");
		
		if(!(list.isEmpty())) {
			
			System.out.println("===============================================");
			for(UserDTO user: list) {
				System.out.print(user.getUserName() + "님의 정보 !");
				System.out.print("\n아이디 : " + user.getUserId());
				System.out.print("\t가입일 : " + user.getEnrollDate());
				System.out.println();
			}
			
			System.out.println("===============================================");
			
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}
		System.out.println();
	}
	
	
	
	
	/**
	 * 2. TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	 */
	private void insertUser() {
		
		System.out.println("--- 회원 추가 페이지입니다. ---");
		

		/*
		 * UNIQUE 했다고 치고 입력받은 아이디 가직 DB가서 WHERE 조건절에다가
		 * 사용자가 입력한 아이디 넣어서 조회결과 있으면 혼쭐내주기
		 */
		System.out.print("아이디를 입력하세요 > ");
		String userId = sc.nextLine();
//		if(userId.length() > 30) {
//			System.out.println("아이디는 30자 이내로 입력해주세요.");
//		}
		
		
		System.out.print("비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();
	
		System.out.print("이름을 입력하세요 > ");
		String userName = sc.nextLine();
	
		int result = userController.insertUser(userId, userPw, userName);
		
		if(result > 0) {
			System.out.println(userName + "님 가입에 성공하셨습니다!");
		} else {
			System.out.println("회원 추가에 실패했습니다. 다시 시도해주세요~");
		}
		System.out.println();
	
	}
	
	
	
	
	/*
	 * 3. 비밀번호를 수정할 아이디, 수정할 비밀번호를 입력받고, 
	 * 바뀌었는지 출력하는 화면 
	 */
	private void pwUpdate() {
		
		System.out.println("--- 비밀번호 수정 페이지입니다 ---");
		
		System.out.print("비밀번호를 수정할 아이디를 입력하세요 > ");
		String userId = sc.nextLine();
	
		System.out.print("수정할 비밀번호를 입력하세요 > ");
		String newPw = sc.nextLine();
		
		int result = userController.pwUpdate(userId,newPw);
		
		if(result > 0) {
			System.out.println("비밀번호가 변경되셨습니다.");
		} else {
			System.out.println("회원 비밀번호 변경에 실패하셨습니다.");
		}	
		System.out.println();
	}
	
	 
	
	/*
	 * 4. 회원 삭제 ( 회원 번호는 중복 x이므로 회원 번호를 받아서 삭제 )
	 */
	
	private void userDelete() {
		
		System.out.println("--- 회원 삭제 페이지입니다 ---");
		
		System.out.print("삭제할 회원의 번호를 입력하세요 > ");
		int userNo = sc.nextInt();
		
		int result = userController.userDelete(userNo);
		
		if(result > 0) {
			System.out.println(userNo + "번 회원이 삭제되었습니다.");
		} else {
			System.out.println(userNo + "번 회원이 존재하지 않습니다. ");
		}
		
	}
	
	
	
	/*
	 * 5. 회원번호를 입력받아 일치하는 회원이 있으면 그 회원의 정보 조회
	 */
	private void numFind() {
		
		System.out.println("--- 회원번호를 입력받아 회원 정보를 "
				+ "조회하는 페이지 입니다 ---");
		
		System.out.print("정보를 조회할 회원의 번호를 입력하세요 > ");
		int userNo = sc.nextInt();
		
		if(!!!userController.numFind(userNo).isEmpty()) {
			for(UserDTO i : userController.numFind(userNo)) {
				System.out.print(userNo+"번의 회원 정보 : ");
				System.out.println(i);
			}
		} else {
			System.out.println(userNo + "번 회원이 존재하지 않습니다. ");
		}
		System.out.println();
	}
	
	
	
	
	/*
	 * 6. 아이디를 입력받아 일치하는 회원이 있으면 그 회원의 정보 조회
	 */
	private void idFind() {
		
		System.out.println("--- 아이디를 입력받아 회원 정보를 "
				+ "조회하는 페이지 입니다 ---");
		
		System.out.print("정보를 조회할 회원의 아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		
		if(!!!userController.idFind(userId).isEmpty()) {
			for(UserDTO i : userController.idFind(userId)) {
				System.out.printf("아이디가 %s인 회원의 정보 : ",userId);
				System.out.println(i);
			}
		} else {
			System.out.printf("아이디가 %s인 회원이 존재하지 않습니다. \n",userId);
		}
		System.out.println();
	}
	
}	
	
	
	
	
	

