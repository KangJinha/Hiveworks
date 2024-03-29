package com.dna.hiveworks.serviceimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dna.hiveworks.model.dao.BoardDao;
import com.dna.hiveworks.model.dto.board.Board;
import com.dna.hiveworks.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	private final SqlSession session;
	
	private final BoardDao dao;
	
	@Override
	public Board selectBoardByNo(int boardNo) {
		return dao.selectBoardByNo(session, boardNo);
	}
	@Override
	public List<Board> selectAllBoard(String boardType) {
	    return dao.selectAllBoard(session,boardType);
	}
	@Override
	@Transactional
	public int insertBoard(Board b) {
		
		log.debug("Board files: {}", b.getFiles());
		int result=dao.insertBoard(session,b);
		if(result>0) {
			if(b.getFiles().size()>0) {
				b.getFiles().forEach(file->{
					file.setBoardNo(b.getBoardNo());
					int fileResult=dao.insertUploadfile(session,file);
					if(fileResult==0) throw new RuntimeException("첨부파일등록실패!");
				});
			}
		}else {
			throw new RuntimeException("첨부파일등록실패!");
		}
		return result;
		
	}

	@Override
	public int boardUpdate(Board b) {
		return dao.boardUpdate(session,b);
	}
	@Override
	public int boardDelete(int boardNo) {
		return dao.boardDelete(session, boardNo);
		
	}
	
	@Override
	@Transactional
	public int updateBoardCount(int boardNo) {
	    log.debug("조회수: {}", boardNo);
	    if(boardNo > 0) {
	        int result = dao.updateBoardCount(session, boardNo); // 실제 조회수 업데이트 메서드 호출
	        if (result > 0) {
	            // 여기에 필요한 로직을 추가할 수 있습니다.
	        } else {
	            throw new RuntimeException("조회수 업데이트 실패!");
	        }
	    } else {
	        throw new RuntimeException("번호가 안들어가짐");
	    }
	    return boardNo;
	}

	
}
	
	

