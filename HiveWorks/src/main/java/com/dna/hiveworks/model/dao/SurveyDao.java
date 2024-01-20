package com.dna.hiveworks.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dna.hiveworks.model.dto.board.Survey;
import com.dna.hiveworks.model.dto.board.SurveyQuestion;

public interface SurveyDao {

	Survey selectSurveyByNo(SqlSession session, int surveyNo);

	List<Survey> selectAllSurvey(SqlSession session);

	int insertSurvey(SqlSession session, Survey s);

	int insertQuestion(SqlSession session, SurveyQuestion qustion);
}
