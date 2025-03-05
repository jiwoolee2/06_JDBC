package com.kh.mvc.controller;

import java.util.List;
import java.util.Map;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

public class UserController {

	/**
	 * VIEW에서 온 요청을 처리해주는 클래스입니다.
	 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달합니다.
	 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환합니다.
	 * @return 
	 */

		
		private UserDAO userDao = new UserDAO();
		private MemberService userService = new MemberService();
		
		
		public List<UserDTO> findAll() {
			
			return userService.findALL();
		}
		
		public int insertUser(String userId, String userPw, String userName) {
			
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			int result = userDao.insertUser(user);
			user = null;
			return result;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

