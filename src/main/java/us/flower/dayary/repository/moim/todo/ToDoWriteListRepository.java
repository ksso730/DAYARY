package us.flower.dayary.repository.moim.todo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.ToDoWriteList;

public interface ToDoWriteListRepository extends JpaRepository<ToDoWriteList, Long>{
	
	//todo세부 갯수
	int countByToDoWrite_id(long id);
	int countByCheckConfirmAndToDoWrite_id(char Y,long id);
	List<ToDoWriteList> findByToDoWrite_id(long id);
	void deleteByToDoWrite_id(long id);
}
