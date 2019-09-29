package us.flower.dayary.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.PeopleService;
import us.flower.dayary.service.community.CommunityBoardService;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@Controller
public class CommunityBoardController {

	@Autowired CommunityBoardRepository communityBoardRepository;

	@Autowired
	CommunityBoardService communityBoardService;

	@Autowired
	PeopleRepository peopleRepository;


	/**
	 * 커뮤니티/스터디리스트 조회
	 *
	 * @param
	 * @return
	 * @throws
	 * @author minholee
	 */
	@GetMapping("/community/communityList/studyList/{board_group_no}")
	public String studyList(@PathVariable("board_group_no") long board_group_no, Model model,
							@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {

		// board group
		model.addAttribute("board_group_no",board_group_no);
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(board_group_no);

		// session check
		session.setAttribute("page", pageable.getPageNumber());

		// 이전에 보고있던 화면이 2page 이상일 경우

		// page
		Page<CommunityBoard> communityStudyList = communityBoardRepository.findAllByBoardGroupAndDeleteFlag(boardGroup, 'N',pageable);
		model.addAttribute("communityStudyList", communityStudyList.getContent());

		//Long communityStudyListCount = communityBoardRepository.countByBoardGroupAndDeleteFlag(boardGroup, 'N');
		model.addAttribute("communityStudyListCount", communityStudyList.getTotalElements());

		// page number
		model.addAttribute("pageNumber", communityStudyList.getTotalPages());

		return "community/comunitystudyList";
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

		Map<String, Object> returnData = new HashMap<>();

		// 게시글 작성자와 현재 세션의 사용자 아이디 체크
		Long peopleId = (Long) session.getAttribute("peopleId");
		Long writerId = communityBoardRepository.getOne(board_id).getPeople().getId();

		if(peopleId.longValue()==writerId.longValue()){
			try {
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
	 *
	 * @param board_group_no
	 * @param board_id
	 * @param session
	 * @param model
	 * @return 게시판 글 디테일
	 */
	@GetMapping("/community/communityList/studyDetail/{board_group_no}/{board_id}")
	public String studyDetail(@PathVariable("board_group_no") long board_group_no, @PathVariable("board_id") long board_id,
							  HttpSession session, Model model) {

		// board group (게시판 그룹)
		model.addAttribute("board_group_no",board_group_no);

		// communityBoard (게시글)
		CommunityBoard communityBoard = communityBoardRepository.getOne(board_id);
		model.addAttribute("communityBoard", communityBoard);

		// people name (작성자 정보)
		People writer = communityBoard.getPeople();
		model.addAttribute("name", writer.getName());

		// people name (사용자 정보)
		Long peopleId = (Long) session.getAttribute("peopleId");
		Long writerId = writer.getId();

		// 작성자 == 사용자 확인
		if(peopleId.longValue()==writerId.longValue()){
			model.addAttribute("writerFlag", TRUE);
		}else{
			model.addAttribute("writerFlag", FALSE);
		}

		// set session page number (이전페이지 돌아갈때 사용)
		model.addAttribute("page", session.getAttribute("page"));

		return "community/comunitystudyDetail";
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
