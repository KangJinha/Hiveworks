package com.dna.hiveworks.service;

import java.util.List;

import com.dna.hiveworks.model.dto.board.Board;
import com.dna.hiveworks.model.dto.board.Survey;

public interface BoardService {

	Board selectBoardByNo(int boardNo);
	
	List<Board> selectAllBoard(String boardType);
	
	int insertBoard(Board b);
	
	int boardUpdate(Board b);

	int boardDelete(int boardNo);

	int updateBoardCount(int boardNo);

	

}
