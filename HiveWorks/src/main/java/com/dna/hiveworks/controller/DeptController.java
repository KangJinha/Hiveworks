package com.dna.hiveworks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dna.hiveworks.model.dto.Department;
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

	
}
