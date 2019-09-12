package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

	 /**
     * 커뮤니티리스트 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/communityList")
	public String CommunityView() {
		
		return "community/communityList";
	}
	 /**
     * 커뮤니티 StudyCafeList 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/studycafeList")
	public String studcafeList() {
		
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
