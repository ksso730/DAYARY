package us.flower.dayary.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.community.BoardLikeRepository;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.community.CommunityBoardService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@Controller
public class CommunityBoardController {

	@Autowired
	CommunityBoardRepository communityBoardRepository;
	@Autowired
	CommunityBoardService communityBoardService;
	@Autowired
	PeopleRepository peopleRepository;
	@Autowired
	BoardLikeRepository boardLikeRepository;

	/**
	 * 게시판 리스트 조회
	 * 2019-09-30 minholee
	 * @param boardGroupId
	 * @param model
	 * @param pageable
	 * @param session
	 * @return
	 */
	@GetMapping("/community/boardList/{boardGroupId}")
	public String studyList(@PathVariable("boardGroupId") long boardGroupId, Model model,
							@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {

		// board group
		model.addAttribute("boardGroupId",boardGroupId);

		// session check
		session.setAttribute("page", pageable.getPageNumber());

		// service
		Page<CommunityBoard> communityBoardList = communityBoardService.getCommunityBoardList(boardGroupId, pageable);

		// contents list
		model.addAttribute("boardList", communityBoardList.getContent());

		// total element count
		model.addAttribute("boardListCount", communityBoardList.getTotalElements());

		// page number
		model.addAttribute("pageNumber", communityBoardList.getTotalPages());

		return "community/boardList";
	}

	/**
	 * 게시판 게시글 삭제
	 * @param board_id
	 * @param session
	 * @return
	 */
	@ResponseBody
	@DeleteMapping("/community/communityList/studyDelete/{board_id}")
	public Map<String, Object> sutdyDelete(@PathVariable("board_id") long board_id,
							  HttpSession session){

		// return message
		Map<String, Object> returnData = new HashMap<>();

		// 세션 사용자 ID
		Long people_id = (Long) session.getAttribute("people_id");

		// 게시글 작성자와 현재 세션의 사용자 같은지
		boolean check = communityBoardService.checkWriter(people_id, board_id);

		// 게시글 작성자가 본인이라면
		if(check){
			try {
				// 게시글 Delete Flag를 'Y'로 변경
				communityBoardService.deleteBoard(board_id);
				returnData.put("code", "1");
				returnData.put("message", "삭제되었습니다");

			} catch (Exception e) {
				returnData.put("code", "E3290");
				returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
		}else {
				returnData.put("code", "E3290");
				returnData.put("message", "게시글 작성자만 삭제할 수 있습니다.");
		}

		return returnData;
	}


	/**
	 * 좋아요 카운트
	 *
	 */
	@ResponseBody
	@PostMapping("/community/communityList/studyLike/{board_group_id}/{board_id}")
	public Map<String, Object> studyLike(@PathVariable("board_id") long board_id, @PathVariable("board_group_id") long board_group_id,
							HttpSession session) {

		Map<String, Object> returnData = new HashMap<>();

		CommunityBoard communityBoard = new CommunityBoard();
		communityBoard.setId(board_id);

		People people = new People();
		Long peopleId = (Long) session.getAttribute("peopleId");
		people.setId(peopleId);

		// 이전 추천했는지 확인
		BoardLike boardLike = boardLikeRepository.findBoardLikeByBoardAndPeople(communityBoard, people);
		if(boardLike==null){
			boardLike = new BoardLike();
			boardLike.setBoard(communityBoard);
			boardLike.setPeople(people);

			BoardGroup boardGroup = new BoardGroup();
			boardGroup.setId(board_group_id);
			boardLike.setBoardGroup(boardGroup);
			boardLikeRepository.save(boardLike);

		}else{
			returnData.put("code", "2");
			returnData.put("message", "이미 추천한 게시글 입니다");
		}

		return returnData;
	}

	/**
	 * 게시글 Detial
	 * 2019-09-30 minholee
	 * @param boardGroupId
	 * @param boardId
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/community/boardDetail/{boardGroupId}/{boardId}")
	public String studyDetail(@PathVariable("boardGroupId") long boardGroupId, @PathVariable("boardId") long boardId,
							  HttpSession session, Model model) {

		// board group (게시판 그룹)
		model.addAttribute("boardGroupId",boardGroupId);

		// communityBoard (게시글)
		CommunityBoard communityBoard = communityBoardService.getCommunityBoard(boardId);
		model.addAttribute("board", communityBoard);

		// 조회수 업데이트
		communityBoardService.addViewCount(communityBoard);

		// 게시글 작성자와 현재 세션의 사용자 같은지
		Long peopleId = (Long) session.getAttribute("peopleId");
		boolean check = communityBoardService.checkWriter(peopleId, communityBoard);

		// 게시글 작성자가 본인이라면
		if(check){
			model.addAttribute("writerFlag", TRUE);
		}else{
			model.addAttribute("writerFlag", FALSE);
		}

		// set session page number (이전페이지 돌아갈때 사용)
		model.addAttribute("page", session.getAttribute("page"));

		return "community/boardDetail";
	}


	/**
	 *
	 * @param board_group_no
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/community/communityList/studyWrite/{board_group_no}")
	public String studyWrite(@PathVariable("board_group_no") long board_group_no, HttpSession session, Model model) {

		// board group
		model.addAttribute("board_group_no",board_group_no);

		// people name
		long peopleId = (Long) session.getAttribute("peopleId");
		People people = peopleRepository.getOne(peopleId);
		model.addAttribute("name", people.getName());

		// set session page number
        model.addAttribute("page", session.getAttribute("page"));

		return "community/comunitystudyWrite";
	}

	/**
     * 커뮤니티리스트 글쓰기
     *
     * @param
     * @return
     * @throws
     * @author minholee
     */
	@ResponseBody
	@PostMapping("/community/communityList/studyWrite/{board_group_no}")
	public Map<String, Object> studyWrite(@PathVariable("board_group_no") long board_group_no, @RequestBody CommunityBoard communityBoard,
							 HttpSession session) {

		Map<String, Object> returnData = new HashMap<String, Object>();

		if (communityBoard.getTitle().equals(null) || communityBoard.getTitle().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "제목을 입력해주세요");
			return returnData;
		}

		if (communityBoard.getMemo().equals(null) || communityBoard.getMemo().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "내용을 입력해주세요");
			return returnData;
		}

		//SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
		//String format_time = format.format (System.currentTimeMillis());

		Long people_no = (Long) session.getAttribute("peopleId");//사용자세션정보 들고오기

		try {
			communityBoardService.communityWrite(people_no,board_group_no,communityBoard);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");

		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
		}

		return returnData;
	}

	/**
	 * 커뮤니티리스트 글 수정
	 *
	 * @param
	 * @return
	 * @throws
	 * @author minholee
	 */
	@ResponseBody
	@PostMapping("/community/communityList/studyModify/{board_group_no}/{board_id}")
	public Map<String, Object> studyModify(@PathVariable("board_group_no") long board_group_no, @PathVariable("board_id") long board_id, @RequestBody CommunityBoard communityBoard,
										  HttpSession session) {

		Map<String, Object> returnData = new HashMap<>();

		if (communityBoard.getTitle().equals(null) || communityBoard.getTitle().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "제목을 입력해주세요");
			return returnData;
		}

		if (communityBoard.getMemo().equals(null) || communityBoard.getMemo().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "내용을 입력해주세요");
			return returnData;
		}


		Long peopleId = (Long) session.getAttribute("peopleId");//사용자세션정보 들고오기
		CommunityBoard modifyBoard = communityBoardRepository.getOne(board_id);
		Long writerId = modifyBoard.getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			try {
				modifyBoard.setTitle(communityBoard.getTitle());
				modifyBoard.setMemo(communityBoard.getMemo());
				communityBoardRepository.save(modifyBoard);
				returnData.put("code", "1");
				returnData.put("message", "수정되었습니다");

			} catch (Exception e) {
				returnData.put("code", "E3290");
				returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
		}else{
			returnData.put("code", "E3290");
			returnData.put("message", "게시글 작성자만 수정할 수 있습니다.");
		}


		return returnData;
	}

	 /**
     * 커뮤니티 StudyCafeList 조회
     *
     * @param
     * @return
     * @throws
     * @author choiseongjun
     */
	@GetMapping("/community/studycafeList/{board_group_no}")
	public String studcafeList(@PathVariable("board_group_no") long board_group_no) {

		return "community/studycafeList";
	}
	 /**
     * 커뮤니티 StudyCafeDetail 조회
     *
     * @param
     * @return
     * @throws
     * @author choiseongjun
     */
	@GetMapping("/community/studycafeDetail")
	public String studycafeDetail() {

		return "community/studycafeDetail";
	}
}
