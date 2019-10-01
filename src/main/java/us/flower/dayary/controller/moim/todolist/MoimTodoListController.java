package us.flower.dayary.controller.moim.todolist;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
import us.flower.dayary.repository.moim.todo.ToDoWriteRepository;
import us.flower.dayary.service.moim.todo.ToDoWriteService;


@Controller
public class MoimTodoListController {
	@Autowired 
	private ToDoWriteService service;
	
	 /**
     * 모임  해야할일(ToDoList) 현재목록  DetailView  조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimtodostatus/moimtodostatusDetail/{no}")
    public String todostatusdetail(@PathVariable("no") long no,Model model) {
    	model.addAttribute("list",service.findByToDoWrite_id(no));
    	model.addAttribute("todo",service.findById(no));
    	return "moim/moimtodostatusDetail";
    }
    @ResponseBody
    @PostMapping("/moimDetail/moimTodoList/moimtodostatus/moimtodostatusDetail")
    public Map<String, Object> todostatusdetailpost(@RequestBody Map<String,String> param) {
    	Map<String, Object> returnData = new HashMap<String, Object>();
    		  try {
    			  service.updateList(param.get("list"));
    	            returnData.put("code", "1");
    	            returnData.put("message", "저장되었습니다");

    	        } catch (Exception e) {
    	            returnData.put("code", "E3290");
    	            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
    	        }
    	      
    	  return returnData;
    }
    /**
     * 모임 일정관리(ToDoList) 완료된것만 보기
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimTodoListcompleted/{no}")
    public String moimTodoListcompleted(@PathVariable("no") long no) {
    	 
    	return "moim/moimTodoListcompleted";
    }
    /**
     * 모임 일정관리(ToDoList) 작성하기
     *
     * @param  
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimTodowrite/{no}")
    public String moimTodowrite(@PathVariable("no") long no,Model model) {
    	 model.addAttribute("no",no);
    	return "moim/moimTodowrite";
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
	@ResponseBody
	@PostMapping("/moimDetail/moimTodoList/moimTodowrite")
	public Map<String, Object> moimTodowrite(HttpSession session,@RequestBody ToDoWriteList todo ) {
		  Map<String, Object> returnData = new HashMap<String, Object>();
		  String id =  (String) session.getAttribute("peopleEmail");
		 
	      
		  try {
	    	  	service.saveList(todo,id);
	            returnData.put("code", "1");
	            returnData.put("message", "저장되었습니다");

	        } catch (Exception e) {
	            returnData.put("code", "E3290");
	            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
	        }
	      
	  return returnData;
	}
    /**
     * 모임 해야할일(ToDoList)에서 달력  조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimcalender")
    public String moimcalender() {
    	
    	return "moim/moimCalender";
    }
    /**
     * 모임 해야할일(ToDoList) 현재목록  조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimtodostatus/{no}")
    public String moimtodostatus(@PathVariable("no") long no,Model model) {
    	
    	model.addAttribute("no",no);
    	model.addAttribute("list", service.findByMoim_id(no));
    	
    	return "moim/moimTodostatus";
    }
	   /**
     * 모임 해야할일(ToDoList) 목록 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/{no}")
    public String moimTodoList(@PathVariable("no") long no,Model model,@PageableDefault Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 9);
    	Page<ToDoWrite> toDolist=service.findByMoim_id(pageable,no);
    	model.addAttribute("no",no);
    	model.addAttribute("todolist", toDolist);
    	return "moim/moimTodoList";
    }
}
