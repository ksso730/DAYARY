package us.flower.dayary.controller.moim.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.common.MediaUtils;
import us.flower.dayary.domain.CommunityBoardReply;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;
import us.flower.dayary.domain.MoimBoardReply;
import us.flower.dayary.domain.UploadFile;
import us.flower.dayary.repository.community.BoardLikeRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.moim.board.MoimBoardService;
import us.flower.dayary.service.moim.image.MoimImageService;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Controller
public class MoimBoardController {
	
	@Autowired
	MoimBoardRepository moimBoardRepository;
	@Autowired
	MoimBoardService moimBoardService;
	@Autowired
	PeopleRepository peopleRepository;
	@Autowired
	BoardLikeRepository boardLikeRepository;
	@Autowired
	MoimImageService moimImageService;

	/**
	 * board group id 구하기
	 * @param boardGroup
	 * @return
	 */
	public long getBoardGroupId(String boardGroup){

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
	 * @param no,boardGroup
	 * @param model
	 * @param pageable
	 * @param search
	 * @param session
	 * @return
     */
    @GetMapping("/moimdetailView/moimboard/{no}/{boardGroup}")
    public String getMoimBoardList(@PathVariable("no") Long no, @PathVariable("boardGroup") String boardGroup,
								   @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=25) Pageable pageable,
								   @RequestParam(name = "search", defaultValue = "") String search,
								   Model model, HttpSession session) {

    	// get board group id
    	long boardGroupId  = getBoardGroupId(boardGroup);
    	// board group
    	model.addAttribute("boardGroup",boardGroup);
    	// session check
    	session.setAttribute("page", pageable.getPageNumber());

		// service
		Page<MoimBoardListDTO> moimBoardList = moimBoardService.getMoimBoardList(no, boardGroupId, pageable, search);
		// contents list
		model.addAttribute("moimBoardList", moimBoardList.getContent());
		// total element count
		model.addAttribute("boardListCount", moimBoardList.getTotalElements());
		// page number
		model.addAttribute("pageNumber", moimBoardList.getTotalPages());

		// search text
		if(search.equals("")){
			model.addAttribute("search", 0);
		}else{
			model.addAttribute("search", search);
		}

    	return "moim/moimBoardList";
    }
    
    /**
	 *  게시글 쓰기 (GET)
	 * @param no
	 * @param boardGroup
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/moimdetailView/moimboard/{no}/{boardGroup}/write")
	public String moimBoardWrite(@PathVariable("no") Long no, @PathVariable("boardGroup") String boardGroup,
								 Model model, HttpSession session) {

		model.addAttribute("boardGroup",boardGroup);

		//long peopleId = (Long) session.getAttribute("peopleId");
		//People people = peopleRepository.getOne(peopleId);
		//model.addAttribute("name", people.getName());

        model.addAttribute("page", session.getAttribute("page"));

		return "moim/moimBoardWrite";
	}

	/**
	 * 게시글 Detail
	 * 2019-09-30 minholee, jino
	 * @param no
	 * @param boardId
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/moimdetailView/moimboard/{no}/{boardGroup}/{boardId}")
	public String getMoimBoardDetail(@PageableDefault Pageable pageable,
									 @PathVariable("no") Long no, @PathVariable("boardGroup") String boardGroup, @PathVariable("boardId") long boardId,
									 Model model, HttpSession session) {

		// board group (게시판 그룹)
		model.addAttribute("boardGroup",boardGroup);

		// moimBoard (게시글)
		MoimBoard moimBoard = moimBoardService.getMoimBoard(boardId);
		model.addAttribute("board", moimBoard);

		// 게시글 작성자와 현재 세션의 사용자 같은지
		Long peopleId = (Long) session.getAttribute("peopleId");
		boolean writer = moimBoardService.checkWriter(peopleId, moimBoard);

		// 게시글 작성자를 제외한 조회 수 업데이트
		if(!writer){
			moimBoardService.addViewCount(moimBoard);
		}

		// 게시글 작성자가 본인이라면
		if(writer){
			model.addAttribute("writerFlag", TRUE);
		}else{
			model.addAttribute("writerFlag", FALSE);
		}

		model.addAttribute("currentPeopleId", peopleId);

		// TRUE 면 기존에 추천한 게시글
		boolean boardLike = moimBoardService.checkBoardLike(peopleId, boardId, getBoardGroupId(boardGroup));
		if(boardLike){
			model.addAttribute("boardLike", TRUE);
		}else{
			model.addAttribute("boardLike", FALSE);
		}

		// set session page number (이전페이지 돌아갈때 사용)
		model.addAttribute("page", session.getAttribute("page"));

		// 댓글 (전체)
		model.addAttribute("replies", moimBoardService.getMoimReplyList(boardId,pageable));

		// 사용자 id (댓글 삭제용)
		model.addAttribute("id", peopleId);

		return "moim/moimBoardDetail";
	}

	/**
	 *  게시글 쓰기 (POST)
	 * @param no, boardGroup, moimBoard, session
     * @return
     * @throws
     * @author yuna
	 */
	@ResponseBody
	@PostMapping("/moimdetailView/moimboard/{no}/{boardGroup}")
	public Map<String, Object> moimBoardWrite(@PathVariable("no") Long no, @PathVariable("boardGroup") String boardGroup,
											  @RequestBody MoimBoard moimBoard,
											  HttpSession session) {

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
		Long boardGroupId = getBoardGroupId(boardGroup);

		try {
			moimBoardService.moimBoardWrite(no, peopleId,boardGroupId, moimBoard);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");

		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
		}

		return returnData;
	}

	/**
	 * 모임 리스트 글 수정
	 *
	 * @param no, boardGroup, boradId, moimBoard, session
	 * @return
	 * @throws
	 * @author jino
	 */
	@ResponseBody
	@PutMapping("/moimdetailView/moimboard/{no}/{boardGroup}/{boardId}")
	public Map<String, Object> studyModify(@PathVariable("no") Long no, @PathVariable("boardGroup") String boardGroup, @PathVariable("boardId") long boardId,
										   @RequestBody MoimBoard moimBoard,
										   HttpSession session) {

		Map<String, Object> returnData = new HashMap<>();

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

		// 게시글 작성자와 현재 세션의 사용자 같은지
		Long peopleId = (Long) session.getAttribute("peopleId");
		boolean check = moimBoardService.checkWriter(peopleId, boardId);

		if(check){
			try {
				moimBoardService.updateBoard(boardId, moimBoard);

				returnData.put("code", "1");
				returnData.put("message", "수정되었습니다");

			} catch (Exception e) {
				returnData.put("code", "E3290");
				returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
		}else{
			returnData.put("code", "E3290");
			returnData.put("message", "게시글 작성자만 수정할 수 있습니다.");
		}


		return returnData;
	}

	/**
	 *  게시판 게시글 삭제
	 * @param boardId
	 * @param session
	 * @return
	 * @thorws
	 * @author jino
	 */
	@ResponseBody
	@DeleteMapping("/moimdetailView/moimboard/{no}/{boardGroup}/{boardId}")
	public Map<String, Object> deleteBoard(@PathVariable("boardId") long boardId,
										   HttpSession session){

		// return message
		Map<String, Object> returnData = new HashMap<>();

		// session user id
		Long peopleId = (Long) session.getAttribute("peopleId");

		// 게시글 작성자와 현재 세션의 사용자 같은지
		boolean check = moimBoardService.checkWriter(peopleId, boardId);

		// 게시글 작성자가 본인이라면
		if(check){
			try {
				// 게시글 Delete Flag를 'Y'로 변경
				moimBoardService.deleteBoard(boardId);
				returnData.put("code", "1");
				returnData.put("message", "삭제되었습니다");

			} catch (Exception e) {
				returnData.put("code", "E3290");
				returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
		}else {
			returnData.put("code", "E3290");
			returnData.put("message", "게시글 작성자만 삭제할 수 있습니다.");
		}

		return returnData;
	}

	/**
	 * 이미지 저장 (2019-10-23)
	 * @param file
	 * @return
	 */
	@ResponseBody
	@PostMapping("/moimdetailView/moimboard/image")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try{
			UploadFile uploadFile = moimImageService.store(file);
			return ResponseEntity.ok().body("/moimdetailView/moimboard/image/" + uploadFile.getId());
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}


	@GetMapping("/moimdetailView/moimboard/image/{fileId}")
	@ResponseBody
	public ResponseEntity<?> serveFile(@PathVariable long fileId) {
		try {
			UploadFile uploadedFile = moimImageService.load(fileId);
			HttpHeaders headers = new HttpHeaders();

			Resource resource = moimImageService.loadAsResource(uploadedFile.getSaveFileName());
			String fileName = uploadedFile.getFileName();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
				headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
			} else {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			}

			return ResponseEntity.ok().headers(headers).body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 *  게시글 추천
	 * @param boardId
	 * @param boardGroup
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping("/moimdetailView/moimboard/{no}/{boardGroup}/like/{boardId}")
	public Map<String, Object> addLike( @PathVariable("boardGroup") String boardGroup, @PathVariable("boardId") long boardId,
										HttpSession session) {

		// return value
		Map<String, Object> returnData = new HashMap<>();

		// people id
		Long peopleId = (Long) session.getAttribute("peopleId");

		// boardGroupId
		Long boardGroupId = getBoardGroupId(boardGroup);

		// 이전 추천했는지 확인 (TRUE 이면 기존 추천글)
		boolean boardLike = moimBoardService.checkBoardLike(peopleId, boardId, boardGroupId);

		// 추천하지 않았던 게시글이면
		if(!boardLike){
			// 추천이력 등록
			moimBoardService.addBoardLike(peopleId, boardId, boardGroupId);
			returnData.put("code", "1");
		}else{
			returnData.put("code", "2");
			returnData.put("message", "이미 추천한 게시글 입니다");
		}

		return returnData;
	}

	/**
	 *  게시글 추천 해제
	 * @param boardId
	 * @param boardGroup
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping("/moimdetailView/moimboard/{no}/{boardGroup}/unlike/{boardId}")
	public Map<String, Object> removeLike( @PathVariable("boardGroup") String boardGroup, @PathVariable("boardId") long boardId,
										   HttpSession session) {

		// return value
		Map<String, Object> returnData = new HashMap<>();

		// people id
		Long peopleId = (Long) session.getAttribute("peopleId");

		// boardGroupId
		Long boardGroupId = getBoardGroupId(boardGroup);

		// 이전 추천했는지 확인 (TRUE 이면 기존 추천글)
		boolean boardLike = moimBoardService.checkBoardLike(peopleId, boardId, boardGroupId);

		// 추천했던 게시글이면
		if(boardLike){
			// 추천이력 제거
			moimBoardService.removeBoardLike(peopleId, boardId, boardGroupId);
			returnData.put("code", "1");
		}else{
			returnData.put("code", "2");
			returnData.put("message", "추천하지않은 게시글 입니다");
		}

		return returnData;
	}

	/**
	 *  댓글 삭제
	 * @param replyId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@DeleteMapping("/moimboards/{boardGroup}/boards/{boardId}/replies/{replyId}")
	public Map<String, Object> deleteReply(@PathVariable("replyId") long replyId,
										   HttpSession session){

		// return message
		Map<String, Object> returnData = new HashMap<>();

		// session user id
		Long peopleId = (Long) session.getAttribute("peopleId");

		// 댓글 작성자와 현재 세션의 사용자 같은지
		boolean check = moimBoardService.checkReplyWriter(peopleId, replyId);

		// 댓글 작성자가 본인이라면
		if(check){
			try {
				returnData.put("code", "1");
				returnData.put("message", "삭제되었습니다");

			} catch (Exception e) {
				returnData.put("code", "E3290");
				returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
		}else {
			returnData.put("code", "E3290");
			returnData.put("message", "게시글 작성자만 삭제할 수 있습니다.");
		}

		return returnData;
	}

	/**
	 * 게시글 댓글저장
	 * @param boardGroup
	 * @param boardId
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@PostMapping("/moimboards/{boardGroup}/boards/{boardId}/replies")
	public HashMap<String, Object> postBoardReply(@PathVariable("boardGroup") String boardGroup, @PathVariable("boardId") long boardId,
												  @RequestBody MoimBoardReply reply, HttpSession session, Model model){
		// RequestBody : MoimBoard / reply ??

		HashMap<String, Object> returnData = new HashMap<>();

		if (reply.getMemo().equals(null) || reply.getMemo().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "내용을 입력해주세요");
			return returnData;
		}

		Long peopleId = (Long) session.getAttribute("peopleId");//사용자세션정보 들고오기
		Long boardGroupId = getBoardGroupId(boardGroup);

		try {
			reply = moimBoardService.addBoardReply(reply, peopleId, boardId, boardGroupId);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");
			// 저장된 댓글 번호
			returnData.put("id", reply.getId());
			returnData.put("memo", reply.getMemo());

		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
		}

		return returnData;
	}
	/**
	 * 게시글 댓글수정
	 * @param reply
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@PutMapping("/moimboards/{boardGroup}/boards/{boardId}/replies/{replyId}")
	public HashMap<String, Object> modifyBoardReply( @PathVariable("replyId") Long replyId,
													 @RequestBody MoimBoardReply reply,
													 HttpSession session, Model model){


		HashMap<String, Object> returnData = new HashMap<>();

		if (reply.getMemo().equals(null) || reply.getMemo().equals("")) {
			returnData.put("code", "0");
			returnData.put("message", "내용을 입력해주세요");
			return returnData;
		}
		reply.setId(replyId);

		try {
			moimBoardService.moidfyBoardReply(reply);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");


		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
		}

		return returnData;
	}
}
