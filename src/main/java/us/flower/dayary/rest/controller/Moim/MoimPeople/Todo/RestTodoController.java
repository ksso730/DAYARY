package us.flower.dayary.rest.controller.Moim.MoimPeople.Todo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
import us.flower.dayary.security.JwtTokenProvider;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.todo.ToDoWriteService;

@RestController
@RequestMapping("/rest")
public class RestTodoController {
	@Autowired 
	private ToDoWriteService todoWriteservice;
	@Autowired 
	private moimService moimService;
	@Autowired
	JwtTokenProvider tokenProvider;
	   /**
     * 모임 해야할일(ToDoList) 목록 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/{MoimId}")
    public ResponseEntity<?> moimTodoList(@PathVariable("MoimId") long MoimId,Model model,@PageableDefault Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 9,Sort.by("id").descending());
    	Page<ToDoWrite> toDolist=todoWriteservice.findByMoim_id(pageable,MoimId);
   // 	boolean moim=todoWriteservice.existByMoim_idAndPeople_id(MoimId,(long)session.getAttribute("peopleId"));
    	JSONObject returnData = new JSONObject();
    	returnData.put("moim",moimService.findMoimone(MoimId).get());
    	returnData.put("todolist", toDolist);
    	//returnData.put("moimPeople",Boolean.toString(moim));
    	returnData.put("count",todoWriteservice.countByMoim_idAndStatus(MoimId));
    	returnData.put("status","allList");
    	return new ResponseEntity<>(returnData, HttpStatus.OK);
    }
    /**
     * 모임  해야할일(ToDoList) 불러와서 조회하기
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @ResponseBody
    @GetMapping("/moimDetail/moimTodoList/detail/{no}")
    public Map<String,Object>  todostatdetail(@PathVariable("no") long no) {
    	JSONObject returnData = new JSONObject();
    	
    	try {
    		returnData.put("list",todoWriteservice.findByToDoWrite_id(no));
    		ToDoWrite todo=todoWriteservice.findById(no);
    		//returnData.put("todo",todo);
    		returnData.put("writer", todo.getPeople());
    		returnData.put("code", "1");
    		returnData.put("message", "조회되었습니다");

	        } catch (Exception e) {
	        	returnData.put("code", "E3290");
	        	returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
	        }
    	
    	
    	return returnData;
    }
    /**
   	 * 모임 일정관리(ToDoList) 작성하기
   	 *
   	 * @param locale
   	 * @param ToDoWriteList
   	 * @return
   	 * @throws 
   	 * @author JY
   	 */
   	@PostMapping(value = "/moimDetail/moimTodoList/moimTodowrite/{no}",consumes = MediaType.APPLICATION_JSON_VALUE)
   	public Map<String, Object> moimTodowrite(@RequestBody ToDoWriteList toDoWriteList ,@PathVariable("no") long no
   			,@RequestHeader (name="Authorization", required=false) String token) {
   		Map<String, Object> returnData = new HashMap<String, Object>();
   		if(tokenProvider.validateToken(token)) {
				System.out.println(tokenProvider.getUserIdFromJWT(token));
				Long PeopleNo=tokenProvider.getUserIdFromJWT(token);
	   		
			//todoWriteservice.saveListTodoRest(todo,PeopleNo,no);
			returnData.put("code", "1");
			returnData.put("message", "저장되었습니다");
			}
   		try {
   		
   			
   		} catch (Exception e) {
   			returnData.put("code", "E3290");
   			returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
   		}
   		
   		return returnData;
   	}
}
