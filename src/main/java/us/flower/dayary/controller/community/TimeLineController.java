package us.flower.dayary.controller.community;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.service.community.CommunityBoardService;

@Controller
public class TimeLineController {
	@Autowired
	CommunityBoardService communityBoardService;
	
	@Autowired
	CommunityBoardRepository communityBoardRepository;
	
	 /**
     * 커뮤니티리스트(Timeline) 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/communityList/findwrittenView/{no}")
	public String CommunityfindwrittenView(@PathVariable("no") Long board_group_id,Model model,HttpSession session) {
	
		long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다.
		List<CommunityBoard> mywrittenList=communityBoardRepository.findByPeople_id(peopleId);
		//sort=sort.and(new Sort(Sort.Direction.DESC));
		
		model.addAttribute("mywrittenList",mywrittenList);
			System.out.println(mywrittenList);
		return "community/communityfindmyinfo";
	}
	/**
     * 커뮤니티리스트(Timeline) 글삭제
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */

	@ResponseBody
	@DeleteMapping("/community/communityList/{board_group_no}/communityDelete/{timeLineListNo}")
	public Map<String, Object> CommunityTimeLinedelete(@PathVariable("timeLineListNo") long timeLineListNo,Model model,Sort sort) {
	
		 Map<String, Object> returnData = new HashMap<String, Object>();


		try {
			communityBoardService.deleteBoardone(timeLineListNo);
		  	returnData.put("code", "1");
            returnData.put("message", "삭제되었습니다");

        } catch (Exception e) {
            returnData.put("code", "E3290");
            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
        }
 

		return returnData;
	}
	 /**
     * 커뮤니티 글쓰기
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@ResponseBody
	@PostMapping("/community/communityList/{board_group_no}/communityWrite")
	public Map<String, Object> CommunityListWrite(@PathVariable("board_group_no") long board_group_no
									,@RequestBody CommunityBoard communityBoard,HttpSession session) {
		
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 
		 if (communityBoard.getMemo().equals(null) || communityBoard.getMemo().equals("")) {
	            returnData.put("code", "0");
	            returnData.put("message", "내용을 입력해주세요");
	            return returnData;
	        }
		 
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
		String format_time = format.format (System.currentTimeMillis());
		
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
     * 커뮤니티리스트(Timeline) 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
	@GetMapping("/community/communityList/{board_group_id}")
	public String CommunityView(@PathVariable("board_group_id") long board_group_id,Model model,Sort sort) {
	
		
		//sort=sort.and(new Sort(Sort.Direction.DESC));
		 
		model.addAttribute("board_group_id",board_group_id);



		List<CommunityBoard> timeLineList=communityBoardService.CommunityList();

		model.addAttribute("timeLineList",timeLineList);
		model.addAttribute("no",board_group_id);
		System.out.println(timeLineList.toString());

		return "community/communityList";
	}
}
