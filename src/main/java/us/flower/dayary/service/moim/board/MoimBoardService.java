package us.flower.dayary.service.moim.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;

public interface MoimBoardService {

	//모임 게시판 글 조회
	//Page<MoimBoardListDTO> getMoimBoardList(Long no, Long boardGroupId, Pageable pageable);
	
	//모임 게시판 글 조회
	List<MoimBoard> getMoimBoardList(long no, long boardGroupId);

	//게시글 쓰기
	void moimBoardWrite(Long peopleId, Long boardGroupId, MoimBoard moimBoard);

}
