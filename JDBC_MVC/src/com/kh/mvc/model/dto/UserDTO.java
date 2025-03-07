package com.kh.mvc.model.dto;

import java.sql.Date;

public class UserDTO {
	
	/*
	 * DB의 테이블에 있는 한 행의 데이터를 담기 위한 목적으로 만듦
	 */
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date enrollDate;
	
	
	
	
	
	public UserDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", enrollDate=" + enrollDate + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
}
