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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


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
		Page<CommunityBoard> communityStudyList = communityBoardRepository.findAllByBoardGroup(boardGroup, pageable);
		model.addAttribute("communityStudyList", communityStudyList.getContent());

		Long communityStudyListCount = communityBoardRepository.countByBoardGroupIs(boardGroup);
		model.addAttribute("communityStudyListCount", communityStudyListCount);

		// page number
		model.addAttribute("pageNumber", communityStudyList.getTotalPages());



		return "community/comunitystudyList";
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
