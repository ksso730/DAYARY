package us.flower.dayary.service.community.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.domain.UploadFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface CommunityImageService {

    public Stream<Long> loadAll();

    public UploadFile load(long fileId);

    public Resource loadAsResource(String fileName) throws Exception;

    public Path loadPath(String fileName);

    public UploadFile store(MultipartFile file) throws Exception;
}

