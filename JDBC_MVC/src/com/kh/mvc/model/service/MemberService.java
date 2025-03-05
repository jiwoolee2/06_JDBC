package com.kh.mvc.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.jdbcUtil;

/**
 * Service : 비즈니스로직 / 의사결정코드를 작성하는 부분
 * 					Controller에서는 Service단의 메서드를 호출하여
 * 					Service에서 실질적으로 동작시켜야하는 코드를 작성
 * 					=> Service단을 추가함으로써 DAO는 순수하게 
 * 						SQL문을 처리하는 부분만 남겨놓을 것
 */
public class MemberService {
	
	private UserDAO userDao = new UserDAO();
	
	public List<UserDTO> findALL(){
		
		Connection conn = jdbcUtil.getConnection();
		
		
		List<UserDTO> list = userDao.findAll(conn);
		
		
		
		
		
		
		
		return list;
	}
}
