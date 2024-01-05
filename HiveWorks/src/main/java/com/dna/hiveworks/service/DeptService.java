package com.dna.hiveworks.service;

import java.util.List;
import java.util.Map;

import com.dna.hiveworks.model.dto.Department;

/**
 * @author : 김태윤
 * @since : 2024. 01. 01.
 * Description : 부서정보 서비스
 * 
 * History :
 * - 작성자 : 김태윤, 날짜 : 2024. 01. 01., 설명 : 최초작성
 * 
 */

public interface DeptService {
	
	List<Department> deptListAll();
	Department selectDeptByName(String deptName);
	int insertDept(Department dept);
	int deleteDept(Department dept);
	int updateDept(Map<String, String> response);
	
}
