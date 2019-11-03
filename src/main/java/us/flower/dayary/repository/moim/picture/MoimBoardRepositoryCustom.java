package us.flower.dayary.repository.moim.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardImage;

public interface MoimBoardRepositoryCustom {
	public Page<MoimBoardImage> search(final Pageable pageable);
}
