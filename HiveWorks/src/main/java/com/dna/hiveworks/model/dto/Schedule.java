package com.dna.hiveworks.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
	private int calNo;
	private String calCode;
	private int myEmpNo;
	private String myEmpName;
	private String myDeptCode;
	private Timestamp calStartDate;
	private Timestamp calEndDate;
	private String calSubject;
	private String calContent;
	private String calColor;
	private String calImportYn;
	private String reminderYn;
	private String calAlldayYn;
	private String calStatus;
	private Date createDate;
	private Date ModifyDate;
	private int creater;
	private int modifier;
	private String useYn;
	private Resource resource;
	private List<InvitationEmp> invitationEmpList;
	private List<CheckList> CheckList;
	
	

}
