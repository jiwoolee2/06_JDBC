package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dto.EmpDTO;
import com.kh.mvc.model.service.EmpService;

public class EmpController {
	
	EmpService empService = new EmpService();
	/*
	 * 1. EmpView에서 조회하고싶은 table을 호출 
	 */
	public List<EmpDTO> select(String[] idList) {
		
		return empService.select(idList);
	}
	
	
	/*
	 * 2.  
	 */
	public int insert(String empId,String empName,String empNo,String email) {
		
		EmpDTO empDto = new EmpDTO();
		empDto.setEmpId(empId);
		empDto.setEmpName(empName);
		empDto.setEmpNo(empNo);
		empDto.setEmail(email);
		
		int result = empService.insert(empDto);
		empDto = null;
		
		return result;
	}
}
