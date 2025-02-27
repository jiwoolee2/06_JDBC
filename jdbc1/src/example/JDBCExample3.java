package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {

	public static void main(String[] args) {
		
		// "최소", "최대" 급여 범위를 입력받아
		
		// EMPLOYEE 테이블에서
		// 급여를 "최소" 이상 "최대" 이하로 받는 사원의
		// 사번, 이름, 부서코드, 급여를 
		// 급여 내림차순으로 출력
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null; // DB 연결 정보 저장, 연결하는 객체
		Statement  stmt = null; // SQL 수행하고, 결과 반환받는 객체 
		ResultSet  rs   = null; // SELECT 수행 결과 저장하는 객체
		
		try {
			
			// 2. DriverManager 객체를 이용해 Connection 객체 생성	
				
			// Oracle JDBC Driver를 메모리에 로드(적재) -> 객체로 만듦
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB 연결 정보
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH18_LJW";
			String password = "KH1234";
				
			conn = DriverManager.getConnection(
					type + host + port + dbName,
					userName, password);
			
			// 범위 입력 받기
			System.out.println("=== 범위 내 급여 받는 직원 조회 ===");
			
			System.out.print("최소 값 입력 : ");
			int min = sc.nextInt();
			
			System.out.print("최대 값 입력 : ");
			int max = sc.nextInt();
			
			
			// 3. SQL 작성
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY ");
			sb.append("FROM EMPLOYEE ");
			sb.append("WHERE SALARY>="+min+"AND SALARY<="+max );
			sb.append("ORDER BY SALARY DESC ");
				
			String sql = sb.toString();
			
			// 4. SQL을 전달하고 결과를 받아올 Statement객체 생성
			stmt = conn.createStatement();
			
			// 5. Statement 객체를 이용해서 SQL을 DB로 전달 후 수행
			rs = stmt.executeQuery(sql);
			
			
			// 6. 조회 결과가 저장된 ResultSet을 1행씩 접근하여
			// 각 행에 기록된 컬럼 값 얻어오기
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptCode = rs.getString("DEPT_CODE");
				int salary = rs.getInt("SALARY");
				
				System.out.printf("사번 : %s / 이름 : %s / 부서코드 : %s  / 급여 : %s\n",
						empId,empName,deptCode,salary);
			
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
