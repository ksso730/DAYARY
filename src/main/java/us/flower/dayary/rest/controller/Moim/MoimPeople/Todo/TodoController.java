package us.flower.dayary.rest.controller.Moim.MoimPeople.Todo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.todo.ToDoWriteService;

@RestController
@RequestMapping("/rest")
public class TodoController {
	@Autowired 
	private ToDoWriteService todoWriteservice;
	@Autowired 
	private moimService moimService;
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
}
