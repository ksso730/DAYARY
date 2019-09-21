package us.flower.dayary.service.community;

import java.util.List;
import java.util.Map;

import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardService {

	void communityWrite(Long people_no, long board_group_no, CommunityBoard communityBoard);


    List<CommunityBoard> CommunityList();


	void deleteBoardone(long timeLineListNo);
}
