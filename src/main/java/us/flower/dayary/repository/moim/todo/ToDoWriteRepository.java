package us.flower.dayary.repository.moim.todo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import us.flower.dayary.domain.ToDoWrite;

public interface ToDoWriteRepository extends JpaRepository<ToDoWrite, Long> {
	//해당모임에 todo가져오기
	Page<ToDoWrite> findByMoim_id(Pageable page,long id);
}
