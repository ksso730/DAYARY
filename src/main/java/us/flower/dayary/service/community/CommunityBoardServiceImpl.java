package us.flower.dayary.service.community;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.*;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.BoardReplyDTO;
import us.flower.dayary.repository.community.BoardLikeRepository;
import us.flower.dayary.repository.community.BoardReplyRepository;
import us.flower.dayary.repository.community.CommunityBoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommunityBoardServiceImpl implements CommunityBoardService{

	@Autowired
	CommunityBoardRepository communityBoardRepository;

	@Autowired
	BoardLikeRepository boardLikeRepository;

	@Autowired
	BoardReplyRepository boardReplyRepository;

	/**
	 *  게시글 작성
	 * @param peopleId
	 * @param boardGroupId
	 * @param communityBoard
	 */
	@Override
	public void communityWrite(Long peopleId, long boardGroupId, CommunityBoard communityBoard) {
		 
		People people=new People();
		people.setId(peopleId);
		
		BoardGroup boardGroup=new BoardGroup();
		boardGroup.setId(boardGroupId);

		communityBoard.setTitle(communityBoard.getTitle());
		communityBoard.setMemo(communityBoard.getMemo());
		communityBoard.setBoardGroup(boardGroup);
		communityBoard.setPeople(people);
		communityBoard.setDeleteFlag("N");
		communityBoardRepository.save(communityBoard);

	}

	/**
	 * 사용자와 게시글 작성자 동일한지 확인
	 * @param peopleId
	 * @param boardId
	 * @return
	 */
	@Override
	public boolean checkWriter(Long peopleId, long boardId) {

		Long writerId = communityBoardRepository.getOne(boardId).getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 사용자와 게시글 작성자 동일한지 확인
	 * @param peopleId
	 * @param communityBoard
	 * @return
	 */
	@Override
	public boolean checkWriter(Long peopleId, CommunityBoard communityBoard) {

		Long writerId = communityBoard.getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 사용자와 댓글 작성자 동일한지 확인
	 * @param peopleId
	 * @param replyId
	 * @return
	 */
	@Override
	public boolean checkReplyWriter(Long peopleId, long replyId) {

		CommunityBoardReply reply = boardReplyRepository.getOne(replyId);

		// 댓글 사용자와 작성자가 같을때
		if(peopleId.longValue()==reply.getPeopleId()){
			deleteReply(reply);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 게시글 목록
	 * @param boardGroupId
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<BoardListDTO> getCommunityBoardList(long boardGroupId, Pageable pageable) {

		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(boardGroupId);

		/*Page<CommunityBoard> communityBoardList = communityBoardRepository.findAllByBoardGroupAndDeleteFlag(boardGroup, "N" ,pageable);*/
		Page<BoardListDTO> communityBoardList = communityBoardRepository.findAllByBoardGroupAndDeleteFlagAndReply(boardGroup, "N" ,pageable);

		return communityBoardList;
	}



	/**
	 * 게시글 목록 (타임라인)
	 * @param boardGroupId
	 * @return
	 */
	@Override
	public List<CommunityBoard> getCommunityBoardList(long boardGroupId) {

		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(boardGroupId);

		List<CommunityBoard> timeLineList = communityBoardRepository.findAllByBoardGroupAndDeleteFlag(boardGroup, "N");

		return timeLineList;
	}

	/**
	 * 본인이 작성한 게시글 조회
	 * @param boardGroupId
	 * @param peopleId
	 * @return
	 */
	@Override
	public List<CommunityBoard> getCommunityBoardList(long boardGroupId, long peopleId) {

		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(boardGroupId);

		People people = new People();
		people.setId(peopleId);

		List<CommunityBoard> timeLineList = communityBoardRepository.findAllByBoardGroupAndDeleteFlagAndPeople(boardGroup, "N", people);

		return timeLineList;
	}

	/**
	 * 게시글 댓글 전체 리스트
	 * @param boardId
	 * @return
	 */
	@Override
	public List<BoardReplyDTO> getCommunityReplyList(long boardId) {

		CommunityBoard board =  getCommunityBoard(boardId);

		List<CommunityBoardReply> communityBoardReplies = boardReplyRepository.getAllByCommunityBoardAndDeleteFlagAndParentIsNull(board, "N");

		List<BoardReplyDTO> replies = new ArrayList<>();
		for(CommunityBoardReply reply : communityBoardReplies){

			// 삭제된 댓글 제외한 리스트
			List<CommunityBoardReply> filteredReply = reply.getChild().stream().filter(child -> child.getDeleteFlag().equals("N")).collect(Collectors.toList());

			BoardReplyDTO replyDTO = new BoardReplyDTO(reply, filteredReply);
			replies.add(replyDTO);
		}

		return replies;

	}


	/**
	 * 게시글 Detail 조회
	 * @param boardId
	 * @return
	 */
	@Override
	public CommunityBoard getCommunityBoard(long boardId) {

		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);

		return communityBoard;
	}

	/**
	 * 게시글 조회수 +!
	 * @param communityBoard
	 */
	@Override
	public void addViewCount(CommunityBoard communityBoard) {
		communityBoard.setViewCount(communityBoard.getViewCount()+1);
		communityBoardRepository.save(communityBoard);
	}



	/**
	 * 게시글 삭제
	 * @param boardId
	 */
	@Override
	public void deleteBoard(long boardId) {
		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);
		communityBoard.setDeleteFlag("Y");
		communityBoardRepository.save(communityBoard);
	}

	/**
	 * 댓글 삭제
	 * @param reply
	 */
	@Override
	public void deleteReply(CommunityBoardReply reply) {
		reply.setDeleteFlag("Y");
		boardReplyRepository.save(reply);
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
		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);
		communityBoard.setHeart(communityBoard.getHeart()+1);
		communityBoardRepository.save(communityBoard);
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
		CommunityBoard communityBoard = communityBoardRepository.getOne(boardId);
		communityBoard.setHeart(communityBoard.getHeart()-1);
		communityBoardRepository.save(communityBoard);
	}

	/**
	 * 댓글 저장
	 * @param reply
	 * @param peopleId
	 * @param boardId
	 * @param boardGroupId
	 */
	@Override
	public CommunityBoardReply addBoardReply(CommunityBoardReply reply, long peopleId, long boardId, long boardGroupId) {

		// parent가 존재하면
		if(reply.getParent()!=null){
			long parentId = reply.getParent().getId();
			CommunityBoardReply parent =  boardReplyRepository.getOne(parentId);

			reply.setParent(parent);
		}

		reply.setPeopleId(peopleId);
		CommunityBoard board = communityBoardRepository.getOne(boardId);
		reply.setCommunityBoard(board);
		reply.setBoardGroupId(boardGroupId);

		// save reply
		boardReplyRepository.save(reply);

		// update board
		board.getCommunityBoardReplies().add(reply);
		board.setReplyCount(board.getReplyCount()+1);
		communityBoardRepository.save(board);

		return reply;
	}
}
