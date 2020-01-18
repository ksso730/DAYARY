package us.flower.dayary.service.moim.board;

import java.util.List;

import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;

@Service
@Transactional
public class MoimBoardServiceImpl implements MoimBoardService{

	@Autowired
	MoimBoardRepository moimBoardRepository;
	
	/**
	 * 모임 게시판 글 조회
	 * yuna
	 * @param boardGroupId
	 * @param pageable
	 * @return
	 */
//	@Override
//	public Page<MoimBoardListDTO> getMoimBoardList(Long no, Long boardGroupId, Pageable pageable) {
//		
//		BoardGroup boardGroup = new BoardGroup();
//		boardGroup.setId(boardGroupId);
//		
//		Page<MoimBoardListDTO> moimBoardList = moimBoardRepository.findByBoardGroup(boardGroupId, pageable);
//
//		return moimBoardList;
//	}

	/**
	 * 게시글 목록
	 * @param boardGroupId
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<MoimBoardListDTO> getMoimBoardList(Long moimId, Long boardGroupId, Pageable pageable, String search) {

		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(boardGroupId);

		Moim moim = new Moim();
		moim.setId(moimId);

		Page<MoimBoardListDTO> moimBoardList = moimBoardRepository.findAllByBoardGroupAndDeleteFlagAndReply(moim, boardGroup, 'N' ,pageable, search);

		return moimBoardList;
	}
	
//	@Override
//	public List<MoimBoard> getMoimBoardList(long no, long boardGroupId) {
//
//		Moim moim = new Moim();
//		moim.setId(no);
//
//		BoardGroup boardGroup = new BoardGroup();
//		boardGroup.setId(boardGroupId);
//
//		List<MoimBoard> moimBoardList = moimBoardRepository.findAllByMoimAndBoardGroupOrderById(moim, boardGroup);
//
//		return moimBoardList;
//	}

	@Override
	public void moimBoardWrite(Long no, Long peopleId, Long boardGroupId, MoimBoard moimBoard) {
		People people=new People();
		people.setId(peopleId);

		Moim moim = new Moim();
		moim.setId(no);
		
		BoardGroup boardGroup=new BoardGroup();
		boardGroup.setId(boardGroupId);

		
		moimBoard.setTitle(moimBoard.getTitle());
		moimBoard.setMemo(moimBoard.getMemo());
		moimBoard.setMoim(moim);
		moimBoard.setBoardGroup(boardGroup);
		moimBoard.setPeople(people);
		moimBoard.setDeleteFlag('N');
		moimBoardRepository.save(moimBoard);
	}


	
	

}
