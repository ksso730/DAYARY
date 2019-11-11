package us.flower.dayary.repository.moim.todo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import us.flower.dayary.domain.ToDoWrite;

public interface ToDoWriteRepository extends JpaRepository<ToDoWrite, Long> {
	//해당모임에 todo가져오기
	List<ToDoWrite> findByMoim_id(long id);
	Page<ToDoWrite> findByMoim_id(Pageable page,long id);
	Page<ToDoWrite> findByMoim_idAndPeople_id(Pageable page,long id,long people);
	int countByMoim_id(long id);
	ToDoWrite findById(long id);
	List<ToDoWrite> findByMoim_idAndStatus(long id, String status,Sort sort);
	List<ToDoWrite> findByMoim_idAndPeople_name(long id, String name,Sort sort);
	List<ToDoWrite> findByMoim_idAndPeople_nameAndStatus(long id, String name,String status,Sort sort);
	int countBymoim_idAndStatus(long id,String status);
}
