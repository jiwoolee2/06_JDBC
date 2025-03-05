package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;

/**
 * DAO (Data Access Object)
 * 
 * 데이터베이스 관련된 직업(CRUD)을 전문적으로 담당하는 객체
 * DAO안에 모든 메소드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를  통해 호출된 기능을 수행
 * DB에 직접 접근한 후 SQL문을 수행하고 결과 받기(JDBC)
 */

public class UserDAO {
	
	/*
	 * JDBC 용 객체
	 * 
	 * - Connection : DB와의 연결정보를 담고있는 객체(IP주소, Port, 사용자명, 비번)
	 * - Statement : Conection이 가지고 있는 연결정보 DB에 
	 * 							 SQL문을 전달하고 실행하고 결과도 받아오는 객체
	 * -> 코드상의 실행 : Statement의 메소드 (executeQuery(), executeUpdate())
	 * -> 나뉘는 이유? select를 실행할 때는 resultSet을 반환,
	 * 								 update,insert,delete의 경우 int로 결과 반환
	 * - ResultSet : 실행한 SQL문이 SELECT문일 경우 조회된 결과가 처음 담기는 객체
	 * 
	 * - PreparedStatement : SQL문을 미리 준비하는 개념
	 * 											 ? (placeholder)로 확보해놓은 공간을
	 * 											 사용자가 입력한 값들과 바인딩해서 SQL문을 수행
	 * 
	 * ** 처리 절차
	 * 
	 * 1) JDBC Driver 등록 : 해당 DBMC에서 제공하는 클래스를 동적으로 등록
	 * 2) Connection 객체 생성 : 접속하고자하는 DB의 정보를 입력해서 생성
	 * 													 (URL, 사용자이름, 비밀번호)
	 * 3_1) PreparedStatement 객체 생성 :
	 * 		  Connection 객체를 이용해서 생성(미완성된 SQL문을 미리 전달)
	 * 3_2) 미완성된 SQL문을 완성형태로 만들어주어야 함
	 * => 미완성된 경우에만 해당 / 완성된 경우에는 생략 
	 * 4) SQL문을 실행 : executeXXXX() => SQL을 인자로 전달하지 않음!
	 * 									 > SELECT(DQL) : executeQuery()
	 * 									 > DML    		 : executeUpdate()
	 * 5) 결과받기 :
	 * 								> SELECT : ResultSet타입 객체(조회데이터담김)
	 * 							  > DML    : int(처리된 행의 개수)
	 * 
	 * 6_1) ResultSet에 담겨있는 데이터들을 하나하나씩 뽑아서 DTO객체 필드에
	 * 			옮겨담은 후 조회 결과가 여러 행일 경우 List로 관리
	 * 6_2) 트랜잭션 처리
	 * 
	 * 7(생략될 수 있음) ) 자원반납(close) => 생성의 역순으로 
	 * 8) 결과반환 -> Controller
	 * 								SELECT > 6_1에서 만든거
	 * 								DML : 처리된 행의 개수 
	 */
	
	private final String URL = "oracle.jdbc.driver.OracleDriver";
	private final String USERNAME = "KH18_LJW";
	private final String PASSWORD = "KH1234";
	
	
	static { // 딱 한 번만 동작함
		
		/*
		 * 예외처리? 정말 심각한거 말고 돌리다보면 별의별 문제들이 일어남/
		 * 쪼잔한 문법 같은거 말고 프로그램 실행중에 발생하는 이런저런문제들
		 * ex) 숫자만 넣어야하는데 문자를 넣는경우
		 * 이건 막을수가 없음. 숫자만 써야하는데 문자를 입력하면 어떻게 막아
		 * 그럼에도 불구하고 예외상황이 발생했을 때 프로그램은 정상적으로 동작해야함. 
		 * 그러기 위해서 코드상으로 작업을 해 주는것을 예외처리라고 함...
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List<UserDTO> findAll(Connection conn) {
		
		// DB가야지~~
		/*
		 * VO / DTO / Entity
		 * 
		 * 1명의 회원의 정보는 1개의 UserDTO객체의 필드에 값을 담아야겠다.
		 * 
		 * 문제점 : userDTO가 몇개가 나올지 알 수 없음
		 */
		
		List<UserDTO> list = new ArrayList();
		String sql = "SELECT "
										+		"USER_NO"
										+ ", USER_ID"
										+	", USER_PW"
										+	", USER_NAME"
										+	", ENROLL_DATE "
							 + "FROM "
										+ "TB_USER "
							 + "ORDER " + "BY "
									  + "ENROLL_DATE DESC";
		
		/*
		 * null로 초기화 해야되는 이유?
		 * -> 메서드 영역에서 선언된 변수는 stack영역에 생성됨
		 * -> 값이 없을 때 아래 try구문에서 오류 발생하면 아래 catch,finally로 내려가는데
		 * -> 값이 안들어갔는데 close를 할 수 없음..
		 */
		
		//Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE","KH18_LJW","KH1234");
			
			
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			
			 //rset.next(); 다음행이 존재하면 true 반환, 아니면 false 반환
			while(rset.next()) {
				// 조회 결과 컬럼 값을 DTO필드에 담는 작업 및 리스트에 요소로 추가
				// DB에서 뽑아낸 테이블의 데이터를 UserDTO에서 필드에 만든 변수에 담아야함 
				// 값을 담으려면 값이 메모리에 올라가 있어야 함. 
				// UserDTO 객체를 생성하여 메모리에 올림
				
				UserDTO user = new UserDTO(); // 값을 담을 UserDTO 객체 생성
				
				user.setUserNo(rset.getInt("USER_NO"));
				user.setUserId(rset.getString("USER_ID"));
				user.setUserPw(rset.getString("USER_PW"));
				user.setUserName(rset.getString("USER_Name"));
				user.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				//
				list.add(user);
				
			}
			
			
			
			
		} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("-=-=-=");
		} 
		/* finally가 의미가 있으려면 위에 return구문이 있어야 함. 
		 * finally는 return 이 앞에 있어도 무조건 실행되어야 하기 때문에..
		 * 
		 * 위에 return구문이 없어도 finally를 쓰는 이유는??
		 * -> 보기편함/ finally에 있는 내용은 무조건 실행되어야하는 내용이구나
		 * 하고 파악할 수 있음.
		 * 
		 */
		finally {
				
				try {
					if(rset != null) rset.close();
					if(pstmt != null) rset.close();
					if(conn != null) rset.close();
				} catch (SQLException e) {
					System.out.println("ddd");
				}
			
		}
	
		return list;
		
	}
	
	
	/*
	 * @param user 사용자가 입력한 아이디/비밀번호/이름이 각각 필드에 대입되어있음
	 * @return 아직 뭐 돌려줄지 안정함
	 */
	public int insertUser(UserDTO user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT "
								+ " INTO "
									   + "TB_USER "
								+ "VALUES "								
									   	+ "("
									   	+ "SEQ_USER_NO.NEXTVAL"
									   	+ ", ?"
									   	+ ", ?"
									   	+ ", ?"
									   	+ ", SYSDATE"
									   	 + ")";
		int result = 0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
					"KH18_LJW","KH1234");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null && !pstmt.isClosed()) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
}
