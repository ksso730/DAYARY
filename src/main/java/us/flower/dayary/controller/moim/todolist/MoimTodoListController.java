package us.flower.dayary.controller.moim.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MoimTodoListController {
	 /**
     * 모임  해야할일(ToDoList) 현재목록  DetailView  조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     */
    @GetMapping("/moimDetail/moimTodoList/moimtodostatus/{no}/moimtodostatusDetail")
    public String todostatusdetail(@PathVariable("no") long no) {
    	
    	return "moim/moimtodostatusDetail";
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
    @GetMapping("/moimDetail/moimTodoList/moimTodowrite")
    public String moimTodowrite() {
    	 
    	return "moim/moimTodowrite";
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
    public String moimTodoList(@PathVariable("no") long no,Model model) {
    	
    	
    	model.addAttribute("no",no);
    	return "moim/moimTodoList";
    }
}
