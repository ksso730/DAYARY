package us.flower.dayary.service.community.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.domain.UploadFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface CommunityImageService {

     Stream<Long> loadAll();

     UploadFile load(long fileId);

     Resource loadAsResource(String fileName) throws Exception;

     Path loadPath(String fileName);

     UploadFile store(MultipartFile file) throws Exception;
}

