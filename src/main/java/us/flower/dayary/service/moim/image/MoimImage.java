package us.flower.dayary.service.moim.image;

import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.MoimBoard;

public interface MoimImage {
	public String[] saveFile(MoimBoard moimBoard,MultipartFile[] file);
	public boolean writePost(long peopleId,long boardId,long moidId, String title,MultipartFile[] file);
}
