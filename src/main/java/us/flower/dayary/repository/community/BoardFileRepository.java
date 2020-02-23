package us.flower.dayary.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.UploadFile;


public interface BoardFileRepository extends JpaRepository<UploadFile, Long> {

    UploadFile findOneByFileName(String fileName);

    UploadFile findById(long fileId);
}
