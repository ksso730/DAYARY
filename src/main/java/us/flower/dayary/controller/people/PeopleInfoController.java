package us.flower.dayary.controller.people;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.people.PeopleInfoService;

@Controller
public class PeopleInfoController {

	@Autowired
	PeopleInfoService peopleinfoService;
	@Autowired
	PeopleRepository peopleRepository;

  /**
   * 사진 불러오기 by choiseongjun
   *
   * @param 
   * @return
   * @throws 
   * @author choiseongjun
   * @date 2019-10-01
   */
	@ResponseBody
    @GetMapping("/getPeopleImage/{imageName:.+}")
    public byte[] getMoimImage(@PathVariable("imageName") String imageName) throws Exception {
        return peopleinfoService.getMoimImage(imageName);
    }
	
	  /**
     * 모임 삭제  by choiseongjun 
     *
     * @param peopleId
     * @return
     * @throws 
     * @author choiseongjun
     * @date 2019-10-01
     */
	@Transactional
	@ResponseBody
	@DeleteMapping("/myprofileView/deletePeople/{peopleId}")
	public Map<String, Object> MoimDeleteOne(@PathVariable("peopleId") long peopleId,Model model,HttpServletRequest request) {
	
		Map<String, Object> returnData = new HashMap<String, Object>();

		
		try {
			
			peopleinfoService.deletePeople(peopleId);//회원탈퇴
			
		  	returnData.put("code", "1");
            returnData.put("message", "삭제되었습니다");

        } catch (Exception e) {
            returnData.put("code", "E3290");
            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
        }
 

		return returnData;
	}
	/**
	 * 내 정보 조회
	 *
	 * @param
	 * @return
	 * @throws @author choiseongjun
	 */
	@GetMapping("/myprofileView")
	public String myprofileView(HttpSession session,Model model) {
		long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다.
		
		peopleRepository.findById(peopleId).ifPresent(peopleinfo->model.addAttribute("peopleinfo",peopleinfo));;
		
		
		return "people/myprofile";
	}
}
