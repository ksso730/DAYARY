package us.flower.dayary.service.moim.board;

import java.util.List;

import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.flower.dayary.domain.*;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;
import us.flower.dayary.repository.community.BoardLikeRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;

@Service
@Transactional
public class MoimBoardServiceImpl implements MoimBoardService{

	@Autowired
	MoimBoardRepository moimBoardRepository;

	@Autowired
	BoardLikeRepository boardLikeRepository;


	/**
	 * 게시글 Detail 조회
	 * @param boardId
	 * @return
	 */
	@Override
	public MoimBoard getMoimBoard(long boardId) {

		MoimBoard moimBoard = moimBoardRepository.getOne(boardId);

		return moimBoard;
	}

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

	/**
	 * 게시글 조회수 +!
	 * @param moimBoard
	 */
	@Override
	public void addViewCount(MoimBoard moimBoard) {
		moimBoard.setViewCount(moimBoard.getViewCount()+1);
		moimBoardRepository.save(moimBoard);
	}

	/**
	 * 사용자와 게시글 작성자 동일한지 확인
	 * @param peopleId
	 * @param boardId
	 * @return
	 */
	@Override
	public boolean checkWriter(Long peopleId, long boardId) {

		Long writerId = moimBoardRepository.getOne(boardId).getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 사용자와 게시글 작성자 동일한지 확인
	 * @param peopleId
	 * @param moimBoard
	 * @return
	 */
	@Override
	public boolean checkWriter(Long peopleId, MoimBoard moimBoard) {

		Long writerId = moimBoard.getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 게시글 추천 이력 조회
	 * @param peopleId
	 * @param boardId
	 * @param boardGroupId
	 * @return
	 */
	@Override
	public boolean checkBoardLike(long peopleId, long boardId, long boardGroupId) {

		BoardLikeId boardLikeId = new BoardLikeId();
		boardLikeId.setPeopleId(peopleId);
		boardLikeId.setCommunityBoardId(boardId);
		boardLikeId.setBoardGroupId(boardGroupId);

		BoardLike boardLike =  boardLikeRepository.findBoardLikeById(boardLikeId);

		// null이면 추천한 이력이 없다
		if(boardLike==null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 게시글 추천
	 * @param peopleId
	 * @param boardId
	 * @param boardGroupId
	 */
	@Override
	public void addBoardLike(long peopleId, long boardId, long boardGroupId) {
		// Embeddable Id
		BoardLikeId boardLikeId = new BoardLikeId();
		boardLikeId.setPeopleId(peopleId);
		boardLikeId.setCommunityBoardId(boardId);
		boardLikeId.setBoardGroupId(boardGroupId);

		// board like
		BoardLike boardLike = new BoardLike();
		boardLike.setId(boardLikeId);

		// save
		boardLikeRepository.save(boardLike);

		// update heart count (+)
		MoimBoard moimBoard = moimBoardRepository.getOne(boardId);
		moimBoard.setHeart(moimBoard.getHeart()+1);
		moimBoardRepository.save(moimBoard);
	}

	/**
	 * 게시글 추천 삭제
	 * @param peopleId
	 * @param boardId
	 * @param boardGroupId
	 */
	@Override
	public void removeBoardLike(long peopleId, long boardId, long boardGroupId) {

		// Embeddable Id
		BoardLikeId boardLikeId = new BoardLikeId();
		boardLikeId.setPeopleId(peopleId);
		boardLikeId.setCommunityBoardId(boardId);
		boardLikeId.setBoardGroupId(boardGroupId);

		// board like
		BoardLike boardLike = new BoardLike();
		boardLike.setId(boardLikeId);

		// delete
		boardLikeRepository.delete(boardLike);

		// update heart count (-)
		MoimBoard moimBoard = moimBoardRepository.getOne(boardId);
		moimBoard.setHeart(moimBoard.getHeart()-1);
		moimBoardRepository.save(moimBoard);
	}

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

	/**
	 * 게시글 수정
	 * @param boardId
	 */
	@Override
	public void updateBoard(long boardId, MoimBoard moimBoard) {
		// modify board
		MoimBoard modifyBoard = moimBoardRepository.getOne(boardId);
		modifyBoard.setTitle(moimBoard.getTitle());
		modifyBoard.setMemo(moimBoard.getMemo());
		moimBoardRepository.save(modifyBoard);
	}

	/**
	 * 게시글 삭제
	 * @param boardId
	 */
	@Override
	public void deleteBoard(long boardId) {
		MoimBoard moimBoard = moimBoardRepository.getOne(boardId);
		moimBoard.setDeleteFlag('Y');
		moimBoardRepository.save(moimBoard);
	}
}
