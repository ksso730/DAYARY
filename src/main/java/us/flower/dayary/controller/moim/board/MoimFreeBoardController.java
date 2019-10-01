package us.flower.dayary.controller.moim.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MoimFreeBoardController {

	/**
     * 모임  디테일 게시판(공지사항,가입인사 및 자기소개,자유게시판 등등)  조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimdetailView/moimboard/{no}")
    public String moimboard(@PathVariable("no") long no) {
    	
    	
    	return "moim/moimDetailboard";
    }
    
   
}
