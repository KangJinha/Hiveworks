package com.dna.hiveworks.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dna.hiveworks.model.dto.Message;

public interface MsgDao {
	List<Message> msgList(SqlSession session, int empNo);
	int starMark(SqlSession session, int msgNo);
	int starUnmark(SqlSession session, int msgNo);
	List<String> receiverNames(SqlSession session, List<Integer> empNos);
	String categoryName(SqlSession session, String cateNo);
	int sendMsg(SqlSession session, Map<String,Object> params);
}