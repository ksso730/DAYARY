package us.flower.dayary.repository.moim.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.MoimBoardImage;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;
import us.flower.dayary.domain.Moim;

public interface MoimBoardRepositoryCustom {
	public Page<MoimBoardImage> search(final Pageable pageable);
	public Page<MoimBoardListDTO> findAllByBoardGroupAndDeleteFlagAndReply(final Moim moim, final BoardGroup boardGroup, final char deleteFlag, final Pageable pageable, final String search);
	//Page<MoimBoardListDTO> findMoimBoardList(BoardGroup boardGroup, Pageable pageable);
}
