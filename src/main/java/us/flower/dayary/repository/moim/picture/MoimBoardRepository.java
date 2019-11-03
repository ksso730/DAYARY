package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;

public interface MoimBoardRepository extends JpaRepository<MoimBoard, Long> {
	
	@Query("select count(id) as countId from MoimBoard where moim=(:no)")
	long countAllMoimBoard(@Param("no") long no);
	
	List<MoimBoard> findByMoim_idAndBoardGroup(Moim moim,BoardGroup boardGroup);


	List<MoimBoard> findByMoim_id(long no);

	List<MoimBoard> findByMoim_idAndBoardGroup_id(long no, long boardGroupId);
	
}