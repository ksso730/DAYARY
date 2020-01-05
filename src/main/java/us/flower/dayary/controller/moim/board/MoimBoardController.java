package us.flower.dayary.controller.moim.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;
import us.flower.dayary.service.moim.board.MoimBoardService;

@Controller
public class MoimBoardController {
	
	@Autowired
	MoimBoardRepository moimBoardRepository;
	@Autowired
	MoimBoardService moimBoardService;

	/**
	 * board group id 구하기
	 * @param boardGroup
	 * @return
	 */
	public long getBoargdGroupId(String boardGroup){

		long boardGroupId  = 8L;

		if(boardGroup.equals("free")){
			boardGroupId = 2L;
		}else if(boardGroup.equals("notice")){
			boardGroupId = 8L;
		}else if(boardGroup.equals("greeting")){
			boardGroupId = 9L;
		}

		return boardGroupId;
	}
	
	/**
     * 모임 게시판(공지사항,가입인사 및 자기소개,자유게시판 등등)  조회
    * 2019-12-29 yuna
	 * @param no, boardGroup
	 * @param model
	 * @param pageable
	 * @param session
	 * @return
     */
    @GetMapping("/moimdetailView/moimboard/{no}/{boardGroup}")
    public String getMoimBoardList(@PathVariable("no") long no, @PathVariable("boardGroup") String boardGroup, Model model,
    														@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=25) Pageable pageable, HttpSession session) {
    	
    	long boardGroupId  = getBoargdGroupId(boardGroup);
    	model.addAttribute("boardGroup",boardGroup);
    			
    	session.setAttribute("page", pageable.getPageNumber());
    	
    	//Page<MoimBoardListDTO> moimBoardList = moimBoardService.getMoimBoardList(no, boardGroupId, pageable);
		//model.addAttribute("moimBoardList", moimBoardList.getContent());
		//model.addAttribute("moimBoardListCount", moimBoardList.getTotalElements());
		//model.addAttribute("pageNumber", moimBoardList.getTotalPages());
    	
    	List<MoimBoard> moimBoardList = moimBoardService.getMoimBoardList(no, boardGroupId);
    	model.addAttribute("moimBoardList", moimBoardList);

    	return "moim/moimDetailboard";
    }
    
    /**
	 *  게시글 쓰기 (GET)
	 * @param no, boardGroup
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/moimdetailView/moimboard/{no}/{boardGroup}/write")
	public String moimBoardWrite(@PathVariable("boardGroup") String boardGroup, HttpSession session, Model model) {

		model.addAttribute("boardGroup",boardGroup);

		//long peopleId = (Long) session.getAttribute("peopleId");
		//People people = peopleRepository.getOne(peopleId);
		//model.addAttribute("name", people.getName());

        model.addAttribute("page", session.getAttribute("page"));

		return "moim/moimBoardWrite";
	}
	
	/**
	 *  게시글 쓰기 (POST)
	 * @param no, boardGroup, title, memo
     * @return
     * @throws
     * @author yuna
	 */
	@ResponseBody
	@PostMapping("/moimdetailView/moimboard/{no}/{boardGroup}/write")
	public Map<String, Object> moimBoardWrite(@PathVariable("boardGroup") String boardGroup, @RequestBody MoimBoard moimBoard, HttpSession session) {

		Map<String, Object> returnData = new HashMap<String, Object>();

		if (moimBoard.getTitle().equals(null) || moimBoard.getTitle().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "제목을 입력해주세요");
			return returnData;
		}

		if (moimBoard.getMemo().equals(null) || moimBoard.getMemo().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "내용을 입력해주세요");
			return returnData;
		}

		Long peopleId = (Long) session.getAttribute("peopleId");//사용자세션정보 들고오기
		Long boardGroupId = getBoargdGroupId(boardGroup);

		try {
			moimBoardService.moimBoardWrite(peopleId,boardGroupId, moimBoard);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");

		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
		}

		return returnData;
	}
   
	
}
