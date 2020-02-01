package us.flower.dayary.service.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.CommunityBoardReply;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.BoardReplyDTO;

import java.util.List;

public interface CommunityBoardService {

	// 게시글 쓰기
	void communityWrite(Long peopleId, long boardGroupId, CommunityBoard communityBoard);

	// 게시글 작성자와 사용자 아이디 같은지 확인
	boolean checkWriter(Long peopleId, long communityBoardId);

	// 게시글 작성자와 사용자 아이디 같은지 확인
	boolean checkWriter(Long peopleId, CommunityBoard communityBoard);

	// 게시글 댓글 작성자와 사용자 아이디 같은지 확인
	boolean checkReplyWriter(Long peopleId, long replyId);

	// 게시판 작성글 리스트
	Page<BoardListDTO> getCommunityBoardList(long boardGroupId, Pageable pageable, String search);

	// 타임라인 작성글 리스트
	List<CommunityBoard> getCommunityBoardList(long boardGroupId);

	// 타임라인 본인글 리스트
	List<CommunityBoard> getCommunityBoardList(long boardGroupId, long peopleId);

	// 댓글 리스트 (대댓글은 DeleteFlag가 "N"인 경우만)
	List<BoardReplyDTO> getCommunityReplyList(long boardId, Pageable pageable);

	//대댓없는  타임라인 댓글리스트
	List<CommunityBoardReply> getTimeLineReplyList(long boardId, Pageable pageable);

	// 게시글 Detail
	CommunityBoard getCommunityBoard(long boardId);

	// 조회수 + 1
	void addViewCount(CommunityBoard communityBoard);

	// 게시글 삭제
	void deleteBoard(long boardId);

	// 댓글 삭제
	void deleteReply(CommunityBoardReply reply);

	// 이미 추천한 게시글인지 확인
	boolean checkBoardLike(long peopleId, long boardId, long boardGroupId);

	// 게시글 추천
	void addBoardLike(long people, long boardId, long BoardGroupId);

	// 게시글 추천 삭제
	void removeBoardLike(long peopleId, long boardId, long BoardGroupId);

	// 게시글 댓글 저장
	CommunityBoardReply addBoardReply(CommunityBoardReply reply, long peopleId, long boardId, long boardGroupId);
	
	//게시글 댓글 수정
	void moidfyBoardReply(CommunityBoardReply reply);

}
