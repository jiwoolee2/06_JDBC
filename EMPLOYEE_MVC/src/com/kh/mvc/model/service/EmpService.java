package com.kh.mvc.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.List;

import com.kh.mvc.controller.EmpController;
import com.kh.mvc.model.dao.EmpDAO;
import com.kh.mvc.model.dto.EmpDTO;
import com.kh.mvc.util.EmpUtil;

public class EmpService {

	EmpDAO empDAO = new EmpDAO();
	
	
	/*
	 * 1
	 */
	public List<EmpDTO> select(String[] idList) {

		Connection conn = EmpUtil.getConnection();
		
		return empDAO.select(conn, idList);
	}
	
	
	public int insert(EmpDTO empDto) {

		Connection conn = EmpUtil.getConnection();
		
		return empDAO.insert(conn, empDto);
	}
	
	
	
}
