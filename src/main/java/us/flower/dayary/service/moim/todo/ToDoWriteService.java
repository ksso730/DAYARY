package us.flower.dayary.service.moim.todo;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
public interface ToDoWriteService {

	public void saveList(ToDoWriteList toDoWriteList,String id);
	public Page<ToDoWrite> findByMoim_id(Pageable page,long id); 
}
