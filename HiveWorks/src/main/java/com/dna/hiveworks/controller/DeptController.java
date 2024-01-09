package com.dna.hiveworks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dna.hiveworks.model.dto.Department;
import com.dna.hiveworks.model.dto.Employee;
import com.dna.hiveworks.service.DeptService;

@Controller
public class DeptController {
	
	@Autowired
	private DeptService service;

	@RequestMapping(value="/deptview",method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tree(ModelAndView mav) {
		mav.setViewName("department/deptView");
		return mav;
	}
	
	//🔻🔻 조직도관리 controller 🔻🔻
	
	@RequestMapping(value="/deptlist",method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Department> deptList(Department dept, Model model) throws Exception{
		List<Department> deptList = service.deptListAll();
		return deptList;
	}
	
	@PostMapping("/insertdept")
	@ResponseBody
	public Map<String, String> insertDept(@RequestBody Department dept){
	    
	    int result = service.insertDept(dept);
	    
	    Map<String, String> response = new HashMap<>();
	    if(result > 0) {
	        response.put("status", "success");
	        response.put("deptCode", dept.getDeptCode());
	    } else {
	        response.put("status", "fail");
	    }
	    return response;
	}
	
	@PostMapping("/updatedept")
	@ResponseBody
	public Map<String, String> updateDept(@RequestBody Department dept){
		
		Map<String, String> response = new HashMap<>();
		response.put("code",dept.getDeptCode());
		response.put("name",dept.getDeptName());
		response.put("upstair",dept.getDeptUpstair());
		
		int result = service.updateDept(response);
		
		if(result > 0) {
	        response.put("status", "success");
	    } else {
	        response.put("status", "fail");
	    }
	    
	    return response;
	}
	
	@PostMapping("/deletedept")
	@ResponseBody
	public Map<String, String> deleteDept(@RequestBody Department dept){
		
		Map<String, String> response = new HashMap<>();
		
		int result = service.deleteDept(dept);
		
		if(result > 0) {
	        response.put("status", "success");
	    } else {
	        response.put("status", "fail");
	    }
	    
	    return response;
	}

	//🔻🔻 구성원관리 controller 🔻🔻	
	//구성원리스트
	@GetMapping("/deptemplist")
	@ResponseBody
	public List<Map<String, Object>> deptEmplist(@RequestParam String deptCode){
		List<Map<String, Object>> employees = service.deptEmpList(deptCode);
		System.out.println(employees);
	    return employees;
	}
	
	//부서명 찾기
	@GetMapping("/searchDeptName")
	@ResponseBody
	public String searchDeptName(@RequestParam String deptCode) {
		String response = service.searchDeptName(deptCode);
		return response;
	}
	
	//부서이동
	@PostMapping("/changeEmpDept")
	@ResponseBody
	public Map<String, String> changeEmpDept(@RequestParam String deptCode, @RequestParam List<String> empIds ){
		Map<String,Object>params = new HashMap<>();
		params.put("empIds", empIds);
		params.put("deptCode", deptCode);
		int result = service.changeEmpDept(params);
		
		Map<String, String> response = new HashMap<>();
		if(result > 0) {
			response.put("status", "success");
	    } else {
	    	response.put("status", "fail");
	    }
	    
	    return response;
	}
	
	@PostMapping("/setleader")
	@ResponseBody
	public Map<String, String> setLeader(@RequestBody Map<String, String> leaderIds){
				
		String newLeaderId = leaderIds.get("newLeaderId");
		String oldLeaderId = leaderIds.get("oldLeaderId");
		
	    int resultNew = service.changeDeptLeader(newLeaderId);
	    int resultOld = 0;
	    if(oldLeaderId!=null) {
	    	resultOld = service.changeDeptLeaderOld(oldLeaderId);
	    }
		Map<String, String> response = new HashMap<>();
		if(resultNew > 0 || resultOld > 0) {
			response.put("status", "success");
	    } else {
	    	response.put("status", "fail");
	    }
	    
	    return response;
	}
	
	@PostMapping("/removeleader")
	@ResponseBody
	public Map<String, String> removeLeader(@RequestParam String id){
		
	    int result = service.removeDeptLeader(id);
		
		Map<String, String> response = new HashMap<>();
		if(result > 0) {
			response.put("status", "success");
	    } else {
	    	response.put("status", "fail");
	    }
	    
	    return response;
	}
	
	@PostMapping("/deptout")
	@ResponseBody
	public Map<String, String> deptEmpOut(@RequestBody List<String> ids){
		Map<String, Object> params = new HashMap<>();
	    params.put("ids", ids);

	    int result = service.deptEmpOut(params);
	    
	    Map<String, String> response = new HashMap<>();
	    if(result > 0) {
	        response.put("status", "success");
	    } else {
	        response.put("status", "fail");
	    }
	    
	    return response;
	}
	
	@GetMapping("/searchEmp")
	@ResponseBody
	public List<Employee> searchEmpByName (@RequestParam String name){
		List<Employee> response = service.searchEmpByName(name);
		return response;
	}
	
	@PostMapping("/addEmpDept")
	@ResponseBody
	public Map<String, String> addEmpDept(@RequestBody Employee emp){
		
		int result = service.addEmpDept(emp);
		
		Map<String, String> response = new HashMap<>();
	    if(result > 0) {
	        response.put("status", "success");
	    } else {
	        response.put("status", "fail");
	    }
	    
	    return response;
	}
}
