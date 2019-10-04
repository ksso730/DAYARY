package us.flower.dayary.service.moim.todo;




import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
public interface ToDoWriteService {

	public void saveList(ToDoWriteList toDoWriteList,String id);
	public Page<ToDoWrite> findByMoim_id(Pageable page,long id); 
	public List<ToDoWrite> findByMoim_id(long id); 
	public List<ToDoWriteList> findByToDoWrite_id(long id);
	public ToDoWrite findById(long id);
	public void updateList(String list);
	public boolean existByMoim_idAndPeople_id(long id,long peopleId);
	public void updateById(long id,Date date);
}
