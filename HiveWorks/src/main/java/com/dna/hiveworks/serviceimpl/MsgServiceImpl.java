package com.dna.hiveworks.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dna.hiveworks.model.dao.MsgDao;
import com.dna.hiveworks.model.dto.Message;
import com.dna.hiveworks.service.MsgService;

import lombok.RequiredArgsConstructor;

/**
 * @author : 김태윤
 * @since : 2023. 01. 14.
 * Description : 쪽지함 Service 구현클래스
 * 
 * History :
 * - 작성자 : 김태윤, 날짜 : 2023. 01. 14., 설명 : 최초작성
**/

@Service
@RequiredArgsConstructor
public class MsgServiceImpl implements MsgService {

	private final SqlSession session;
	private final MsgDao dao;
	
	@Override
	public List<Message> msgList(int empNo) {
		
		List<Message> result = dao.msgList(session, empNo);
		
		return result;
	}

	@Override
	public int starMark(int msgNo) {
		
		return dao.starMark(session,msgNo);
	}
	
	@Override
	public int starUnmark(int msgNo) {
		
		return dao.starUnmark(session,msgNo);
	}
		
	@Override
	public List<String> receiverNames(List<Integer> empNos) {
		
		return dao.receiverNames(session,empNos);
	}

	@Override
	public String categoryName(String cateNo) {
		return dao.categoryName(session,cateNo);
	}

	@Override
	public int sendMsg(Map<String, Object> params) {
		
		int count = 0;
		int length = ((List<Integer>) params.get("receiverEmpNo")).size();
		for(int i=0; i<length; i++) {
			
			Map<String,Object> datas = new HashMap<>();
			
			datas.put("receiverEmpNo", ((List<Integer>) params.get("receiverEmpNo")).get(i));
			datas.put("receiverName", ((List<String>) params.get("receiverNames")).get(i));
			datas.put("senderEmpNo",params.get("senderEmpNo"));
			datas.put("msgCategory", params.get("msgCategory"));
			datas.put("msgCategoryName", params.get("msgCategoryName"));
			datas.put("msgTitle", params.get("msgTitle"));
			datas.put("msgContent", params.get("msgContent"));
			datas.put("fileOriname", params.get("fileOriname"));
			datas.put("fileRename", params.get("fileRename"));
			datas.put("fileSize", params.get("fileSize"));
						
			int result = dao.sendMsg(session,datas);
			
			if(result==0) new RuntimeException("쪽지 전송 실패");
			
			count++;
		}
		return count;
		
	}


	
	
}