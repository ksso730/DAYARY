package us.flower.dayary.service.moim.image;

import org.springframework.web.multipart.MultipartFile;

public interface MoimImage {
	public boolean saveFile(MultipartFile[] file);
}
