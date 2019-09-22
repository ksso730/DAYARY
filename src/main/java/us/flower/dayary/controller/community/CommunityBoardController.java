package us.flower.dayary.controller.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.repository.community.CommunityBoardRepository;


@Controller
public class CommunityBoardController {

	@Autowired CommunityBoardRepository communityBoardRepository;
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
							@PageableDefault Pageable pageable) {

		// board group
		model.addAttribute("board_group_no",board_group_no);
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setId(board_group_no);

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
     * 커뮤니티리스트 글쓰기
     *
     * @param
     * @return
     * @throws
     * @author minholee
     */
	@GetMapping("/community/communityList/studyWrite/{board_group_no}")
	public String studyWrite(@PathVariable("board_group_no") long board_group_no) {

		return "community/comunitystudyWrite";
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
