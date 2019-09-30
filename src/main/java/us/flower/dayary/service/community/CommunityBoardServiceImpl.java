package us.flower.dayary.service.community;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.community.CommunityBoardRepository;

import java.util.List;
import java.util.zip.CheckedOutputStream;

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

		communityBoard.setTitle(communityBoard.getTitle());
		communityBoard.setMemo(communityBoard.getMemo());
		communityBoard.setBoardGroup(boardGroup);
		communityBoard.setPeople(people);
		communityBoard.setDeleteFlag('N');
//		communityBoard.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
//		communityBoard.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
		communityBoardRepository.save(communityBoard);

	}

	@Override
	public boolean checkWriter(Long peopleId, long boardId) {

		Long writerId = communityBoardRepository.getOne(boardId).getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkWriter(Long peopleId, CommunityBoard communityBoard) {

		Long writerId = communityBoard.getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public Page<CommunityBoard> getCommunityBoardList(long board_group_no, Pageable pageable) {

		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(board_group_no);

		Page<CommunityBoard> communityStudyList = communityBoardRepository.findAllByBoardGroupAndDeleteFlag(boardGroup, 'N',pageable);

		return communityStudyList;
	}

	// 게시글 Detail 조회
	@Override
	public CommunityBoard getCommunityBoard(long boardId) {

		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);

		return communityBoard;
	}

	// 조회수 +1
	@Override
	public void addViewCount(CommunityBoard communityBoard) {

		communityBoard.setViewCount(communityBoard.getViewCount()+1);
		communityBoardRepository.save(communityBoard);

	}

//	@Override
//	public List<CommunityBoard> CommunityList() {
//		return communityBoardRepository.findAll();
//	}


//	@Override
//	public Page<CommunityBoard> CommunityStudyList(BoardGroup boardGroup, Pageable pageable) {
//		return communityBoardRepository.findAllByBoardGroup(boardGroup, pageable);
//	}

//	@Override
//	public void deleteBoardone(long timeLineListNo) {
//		communityBoardRepository.deleteById(timeLineListNo);
//	}


	@Override
	public void deleteBoard(long boardId) {
		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);
		communityBoard.setDeleteFlag('Y');
		communityBoardRepository.save(communityBoard);
	}
}
