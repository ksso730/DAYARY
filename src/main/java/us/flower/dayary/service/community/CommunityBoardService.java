package us.flower.dayary.service.community;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardService {

	void communityWrite(Long peopleId, long boardGroupId, CommunityBoard communityBoard);

	// 게시글 작성자와 사용자 아이디 같은지 확인
	boolean checkWriter(Long peopleId, long boardId);

	// 게시글 작성자와 사용자 아이디 같은지 확인
	boolean checkWriter(Long peopleId, CommunityBoard communityBoard);

	// 게시판 작성글 리스트
	Page<CommunityBoard> getCommunityBoardList(long boardGroupId, Pageable pageable);

	CommunityBoard getCommunityBoard(long boardId);

	void addViewCount(CommunityBoard communityBoard);

	// 게시글 DELETE FLAG = 'Y'로 변경
	void deleteBoard(long boardId);

    //List<CommunityBoard> CommunityList();

	//void deleteBoardone(long timeLineListNo);


}
