package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommunityController {

	 /**
     * 커뮤니티리스트(Timeline) 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/communityList/{board_group_no}")
	public String CommunityView(@PathVariable("board_group_no") long board_group_no) {
		
		return "community/communityList";
	}
	/**
     * 커뮤니티리스트 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/communityList/studyList/{board_group_no}")
	public String studyList(@PathVariable("board_group_no") long board_group_no,Model model) {
		
		model.addAttribute("board_group_no",board_group_no);
		
		return "community/comunitystudyList";
	}
	/**
     * 커뮤니티리스트 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
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
	 * 커뮤니티 StudyCafeList 작성
	 *
	 * @param
	 * @return
	 * @throws
	 * @author choiseongjun
	 */
	@GetMapping("/community/communityList/studycafeWrite/{board_group_no}")
	public String studyCafeWrite(@PathVariable("board_group_no") long board_group_no) {

		return "community/studycafeWrite";
	}
	 /**
     * 커뮤니티 StudyCafeDetail 상세보기
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
