package us.flower.dayary.service.moim.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;

public interface MoimBoardService {

	//모임 게시판 글 조회
	Page<MoimBoardListDTO> getMoimBoardList(Long moimId, Long boardGroupId, Pageable pageable, String search);

	//게시글 쓰기
	void moimBoardWrite(Long no, Long peopleId, Long boardGroupId, MoimBoard moimBoard);

	////////////////////////////////////////////////////////////////////////////////////

//	// 게시글 작성자와 사용자 아이디 같은지 확인
//	boolean checkWriter(Long peopleId, long moimBoardId);
//
//	// 게시글 작성자와 사용자 아이디 같은지 확인
//	boolean checkWriter(Long peopleId, CommunityBoard moimBoard);
//
//	// 게시글 Detail
//	MoimBoard getMoimBoard(long boardId);

}
