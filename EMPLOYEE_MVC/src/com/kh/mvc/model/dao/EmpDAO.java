package com.kh.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.EmpDTO;
import com.kh.mvc.util.EmpUtil;

public class EmpDAO {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	}
	
	/*
	 * 1
	 */
	public List<EmpDTO> select(Connection conn, String[] idList) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID IN ( ";
	
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		
		try {
			
			for(int a=0 ; a<idList.length ; a++) {
				if(a==idList.length-1) {
					sql += "?)";
				} else {
					sql += "?,";
				}
			}
			
			pstmt = conn.prepareStatement(sql);
			
			
			for(int b=0 ; b<idList.length ; b++) {
				pstmt.setString(b+1,idList[b]);
			}
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				EmpDTO empDto = new EmpDTO();
				empDto.setEmpId(rs.getString("EMP_ID"));
				empDto.setEmpName(rs.getString("EMP_Name"));
				empDto.setEmpNo(rs.getString("EMP_NO"));
				empDto.setEmail(rs.getString("EMAIL"));
				empDto.setPhone(rs.getString("PHONE"));
				empDto.setDeptCode(rs.getString("DEPT_CODE"));
				empDto.setJobCode(rs.getString("JOB_CODE"));
				empDto.setSalLevel(rs.getString("SAL_LEVEL"));
				empDto.setSalary(rs.getInt("SALARY"));
				empDto.setBonus(rs.getInt("BONUS"));
				empDto.setManagerId(rs.getString("MANAGER_ID"));
				empDto.setHireDate(rs.getDate("HIRE_DATE"));
				empDto.setEntDate(rs.getDate("ENT_DATE"));
				empDto.setEntYn(rs.getString("ENT_YN"));
				
				list.add(empDto);
			}
			

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
				EmpUtil.close(rs);
				EmpUtil.close(pstmt);
				EmpUtil.close(conn);
		}
		
		return list;
		
	}
	
	
	/*
	 * 2.
	 */
	public int insert(Connection conn, EmpDTO empDto) {
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO EMPLOYEE(EMP_ID,EMP_NAME,EMP_NO,EMAIL) "
				+ "VALUES(?,?,?,?) ";
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1,empDto.getEmpId());
			pstmt.setString(2,empDto.getEmpName());
			pstmt.setString(3,empDto.getEmpNo());
			pstmt.setString(4,empDto.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
		
		EmpUtil.close(pstmt);
		EmpUtil.close(conn);
		
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
