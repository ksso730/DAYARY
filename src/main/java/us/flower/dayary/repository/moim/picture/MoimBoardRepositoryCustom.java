package us.flower.dayary.repository.moim.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.DTO.MoimBoardImage;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;

public interface MoimBoardRepositoryCustom {
	public Page<MoimBoardImage> search(final Pageable pageable);
	
	//Page<MoimBoardListDTO> findMoimBoardList(BoardGroup boardGroup, Pageable pageable);
}
