package us.flower.dayary.controller.moim.todolist;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
import us.flower.dayary.repository.moim.todo.ToDoWriteRepository;
import us.flower.dayary.service.moim.moimService;
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
    /**
     * 모임  해야할일(ToDoList) 완료된 것 저장
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @ResponseBody
    @PostMapping("/moimDetail/moimTodoList/moimtodostatus/moimtodostatusDetail/{no}")
    public Map<String, Object> todostatusdetailpost(@PathVariable("no") long no,@RequestBody Map<String,String> param) {
    	Map<String, Object> returnData = new HashMap<String, Object>();
    		  try {
    			  service.updateList(param.get("list"),no,Integer.parseInt(param.get("count")));
    	            returnData.put("code", "1");
    	            returnData.put("message", "저장되었습니다");

    	        } catch (Exception e) {
    	            returnData.put("code", "E3290");
    	            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
    	        }
    	      
    	  return returnData;
    }
    /**
     * 모임  해야할일(ToDoList)  저장
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @ResponseBody
    @GetMapping("/moimDetail/moimTodoList/detail/{no}")
    public Map<String,Object>  todostatdetail(@PathVariable("no") long no) {
    	
    	Map<String,Object> data=new HashMap<String,Object>();
    	try {
    		data.put("list",service.findByToDoWrite_id(no));
    		data.put("todo",service.findById(no));
    		data.put("code", "1");
    		data.put("message", "저장되었습니다");

	        } catch (Exception e) {
	        	data.put("code", "E3290");
	        	data.put("message", "데이터 확인 후 다시 시도해주세요.");
	        }
    	
    	
    	return data;
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
    public String moimTodoListcompleted(@PathVariable("no") long no,Model model) {
    	model.addAttribute("list",service.findByMoim_idAndStatus(no, "End"));
    	return "moim/moimTodoListcompleted";
    }
    /**
     * 모임 일정관리(ToDoList) 새로운것만 보기
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @GetMapping("/moimDetail/moimTodoList/moimTodoListNew/{no}")
    public String moimTodoListNew(@PathVariable("no") long no,Model model) {
    	model.addAttribute("list",service.findByMoim_idAndStatus(no,"New"));
    	
    	return "moim/moimTodoListcompleted";
    }
    /**
     * 모임 일정관리(ToDoList) 진행중인것만 보기
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @GetMapping("/moimDetail/moimTodoList/moimTodoListProgress/{no}")
    public String moimTodoListProgress(@PathVariable("no") long no,Model model) {
    	model.addAttribute("list",service.findByMoim_idAndStatus(no,"In Progress"));
    	
    	
    	return "moim/moimTodoListcompleted";
    }
    /**
     * 모임 일정관리(ToDoList) 미완료된것만 보기
     *
     * @param 
     * @return
     * @throws 
     * @author jy
     */
    @GetMapping("/moimDetail/moimTodoList/moimTodoListSuspend/{no}")
    public String moimTodoListSuspend(@PathVariable("no") long no,Model model) {
    	model.addAttribute("list",service.findByMoim_idAndStatus(no,"Suspend"));
    	
    	
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
     * 모임 해야할일(ToDoList) 현재시간 통해 상태 update
     *
     * @param 
     * @return
     * @throws 
     * @author JY
     */
    @GetMapping("/moimDetail/moimTodoList/status/{no}")
    public Map<String,Object> status(@PathVariable("no")long no) {
    	  Map<String, Object> returnData = new HashMap<String, Object>();
    	Date date=new java.sql.Date(System.currentTimeMillis());
	    	service.updateById(no, date);
	    	return returnData;
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
    public String moimTodoList(@PathVariable("no") long no,Model model,@PageableDefault Pageable pageable,HttpSession session) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 9);
    	Page<ToDoWrite> toDolist=service.findByMoim_id(pageable,no);
    	boolean moim=service.existByMoim_idAndPeople_id(no,(long)session.getAttribute("peopleId"));
    	model.addAttribute("no",no);
    	model.addAttribute("todolist", toDolist);
    	model.addAttribute("moimPeople",Boolean.toString(moim));
    	return "moim/moimTodoList";
    }
    /**
     * todo 삭제
    *
    * @param 
    * @return
    * @throws 
    * @author JY
    * @date 2019-10-04
    */
   @ResponseBody
   @DeleteMapping("/moimDetail/moimTodoList/delete/{no}")
   public Map<String, Object> MoimDeleteOne(@PathVariable("no") long no) {
   
      Map<String, Object> returnData = new HashMap<String, Object>();
      
      try {
    	  service.deleteById(no);
           returnData.put("code", "1");
           returnData.put("message", "삭제되었습니다");

       } catch (Exception e) {
           returnData.put("code", "E3290");
           returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
       }


      return returnData;
   }
}
