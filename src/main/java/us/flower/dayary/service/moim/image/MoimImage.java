package us.flower.dayary.service.moim.image;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.DTO.MoimBoardImage;

public interface MoimImage {
	public List<MoimBoardFile> saveFile(MoimBoard moimBoard,MultipartFile[] file);
	public boolean writePost(long peopleId,long boardId,long moidId, String title,MultipartFile[] file);
	public Page<MoimBoard> search(final Pageable pageable,long no);
}
