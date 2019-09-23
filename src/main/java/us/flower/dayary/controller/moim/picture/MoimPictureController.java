package us.flower.dayary.controller.moim.picture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MoimPictureController {

	 /**
     * 모임 사진첩 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     */
    @GetMapping("/moimDetail/{no}/moimPicture")
    public String moimPicture(@PathVariable("no") long no,Model model) {
    	
    	model.addAttribute("no",no);
    	
    	return "moim/moimpictureList"; 
    }
    /**
     * 모임 사진첩 글쓰기
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     */
    @GetMapping("/moimDetail/{no}/moimPicture/moimPictureWrite")
    public String moimPictureWrite(@PathVariable("no") long no,Model model) {
    	
    	model.addAttribute("no",no);
    	
    	return "moim/moimpictureWrite"; 
    }
   
}
