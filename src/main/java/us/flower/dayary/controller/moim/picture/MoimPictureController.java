package us.flower.dayary.controller.moim.picture;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import us.flower.dayary.service.moim.image.MoimImageImpl;

@Controller
public class MoimPictureController {
	@Autowired 
	MoimImageImpl moimiamge;
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
    /*
    @GetMapping("/moimDetail/{no}/moimPicture/moimPictureWrite")
    public String moimPictureWrite(@RequestParam("image") MultipartFile mtfRequest,Model model) {
    	
    	System.out.println("성공");
    	
    	return "moim/moimpictureWrite"; 
    }*/
    @GetMapping("/moimDetail/{no}/moimPicture/moimPictureWrite")
    public String uploadMoimImg(@PathVariable("no") long no,Model model) {
    	
    	model.addAttribute("no",no);
    	
    	return "moim/moimpictureWrite"; 
    }  
    @ResponseBody
    @PostMapping("/moimDetail/test")
    public Map<String,String> testMethod(@RequestPart("file") MultipartFile[] file) {
    	Map<String,String> result = new HashMap<>();
    	String resultStr="";   	
    	boolean check = true;
    	
    	check = moimiamge.saveFile(file);
    	resultStr = check ? "성공" :"실패";
    	result.put("result", resultStr);

    	return result;
    } 
}
