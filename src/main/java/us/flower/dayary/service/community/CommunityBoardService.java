package us.flower.dayary.service.community;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardService {

	void communityWrite(Long people_no, long board_group_no, CommunityBoard communityBoard);


    List<CommunityBoard> CommunityList();
	//Page<CommunityBoard> CommunityStudyList(BoardGroup boardGroup, Pageable pageable);

	void deleteBoardone(long timeLineListNo);
}
