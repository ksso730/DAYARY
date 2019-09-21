package us.flower.dayary.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.CommunityBoardRepository;

import java.util.List;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService{

	@Autowired
	CommunityBoardRepository communityBoardRepository;
	
	@Override
	public void communityWrite(Long people_no, long board_group_id, CommunityBoard communityBoard) {
		 
		People people=new People();
		people.setId(people_no);
		
		BoardGroup boardGroup=new BoardGroup();
		boardGroup.setId(board_group_id);
		
		communityBoard.setMemo(communityBoard.getMemo());
		communityBoard.setBoardGroup(boardGroup);
		communityBoard.setPeople(people);
		communityBoard.setDeleteFlag('N');
		
		communityBoardRepository.save(communityBoard);

	}

	@Override
	public List<CommunityBoard> CommunityList() { 
		return communityBoardRepository.findAll();
	}

	@Override
	public void deleteBoardone(long timeLineListNo) {
		communityBoardRepository.deleteById(timeLineListNo);
	}


}
