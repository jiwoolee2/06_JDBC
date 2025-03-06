package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

/**
 * VIEW에서 온 요청을 처리해주는 클래스입니다.
 * 메소드로 전달된 데이터값을 가공처리한 후 DAO로 전달합니다.
 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환합니다.
 */
public class UserController {
	
	private MemberService userService = new MemberService();
	
	/* 1
	 * */
	public List<UserDTO> findAll() {
		return userService.findAll();
	}
	/* 2
	 * */
	public int insertUser(String userId, String userPw, String userName) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		int result = userService.insertUser(user);
		user = null;
		return result;
	}
	
		
	/* 3
	 * */
		public int pwUpdate(String userId, String newPw) {
			
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			user.setUserPw(newPw);
			
			int result = userService.pwUpdate(user);
			user = null;
			
			return result;
		}
		
		
		/* 4
		 * */
		public int userDelete(int userNo) {
			
			UserDTO user = new UserDTO();
			user.setUserNo(userNo);
			
			int result = userService.userDelete(user);
			
			return result;
			
		}
		

		
		/* 5
		 * */
		public List<UserDTO> numFind(int userNo) {
			
			UserDTO user = new UserDTO();
			user.setUserNo(userNo);
			
			List<UserDTO> list = userService.numFind(user);
			user = null;
			
			return list;
		}
		
		
		
		/* 6
		 * */
		public List<UserDTO> idFind(String userId) {
			
			UserDTO user = new UserDTO();
			user.setUserId(userId);
			
			List<UserDTO> list = userService.idFind(user);
			user = null;
			
			return list;
		}
		
	}

		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

