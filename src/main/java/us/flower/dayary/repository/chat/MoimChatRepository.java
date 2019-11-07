package us.flower.dayary.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.MoimChat;

public interface MoimChatRepository extends JpaRepository<MoimChat, Long>{

	List<MoimChat> findByMoim_id(long no);

	long countByMoim_id(long no);

}
