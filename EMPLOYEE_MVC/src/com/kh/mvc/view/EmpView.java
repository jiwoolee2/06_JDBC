package com.kh.mvc.view;

import java.lang.ModuleLayer.Controller;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.EmpController;
import com.kh.mvc.model.dto.EmpDTO;

public class EmpView {

	Scanner sc = new Scanner(System.in);
	EmpController empController = new EmpController();
	/* 번호 입력할 수 있도록 보기 제공하는 화면 출력 */
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("--- 안녕하세요 ---");
			System.out.println("1. SELECT");
			System.out.println("2. INSERT");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("9. 프로그램 종료");
			
			System.out.print("실행할 프로그램의 번호를 입력하세요 > ");
			int num = sc.nextInt();
			
			sc.nextLine();
			
			switch (num) {
				case 1: select() ; break;
				case 2: insert() ; break;
				case 3: update() ; break;
				case 4: delete() ; break;				
				case 9: System.out.println("프로그램 종료"); return; 	
				default: System.out.println("존재하지 않는 번호입니다.");
		
			}
		}
	}
	
	/*
	 * 1. SELECT
	 * EMP_ID를 입력받아 원하는 컬럼만 조회하기 
	 */
	private void select() {
		
		System.out.println("---------------- SELECT ----------------");
		
		System.out.print("조회하고싶은 사원의 번호를 입력하세요  \n"
										+ "(여러개 입력 시 띄어쓰기로 구분) : ");
		String empId = sc.nextLine();
		String[] idList = empId.split(" ");
		
		List<EmpDTO> list = empController.select(idList);
		System.out.println(list);
	}
	
	
	/*
	 * 2. INSERT
	 * 컬럼값을 입력 받아서 추가
	 */
	private void insert() {
		
		System.out.println("---------------- insert ----------------");
		
		System.out.print("사번 : ");
		String empId = sc.nextLine();

		System.out.print("이름 : ");
		String empName = sc.nextLine();
		
		System.out.print("주민등록번호 : ");
		String empNo = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		int result = empController.insert("empId","empName","empNo","email");
		
		if(result > 0) {
			System.out.println(empName + " 사원이 추가되었습니다.");
		} else {
			System.out.println("추가 실패하였습니다.");
		}
	}
	
	
	/*
	 * 3. UPDATE
	 */
	private void update() {
		
	}
	
	/*
	 * 4. DELETE
	 */
	private void delete() {
		
	}
	
	
	
	
	
	
	
}
