package com.kh.mvc.run;

import java.util.Properties;

import com.kh.mvc.view.UserView;

public class Run {

	public static void main(String[] args) {
		
		/* Model : 데이터 관련된 모든 작업 
		 * View  : 화면 상 입/출력
		 * Controller : View에서의 요청을 받아서 처리해주는 역할
		 */

		new UserView().mainMenu();
	}

}

/* Run에 main에서 시작
 * -> Userview에있는 mainMenu 메서드를 호출하려고하면 필드가 먼저 메모리에 올라감
 * -> 따라서 Scanner, UserController 객체 생성
 * -> UserDAO 객체 생성
 * -> mainMenu 메서드 호출, stack영역에 메서드 올라감
 * 
 */


