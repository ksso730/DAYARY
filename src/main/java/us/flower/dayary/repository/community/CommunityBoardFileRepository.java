package us.flower.dayary.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.flower.dayary.domain.UploadFile;

@Repository
public interface CommunityBoardFileRepository extends JpaRepository<UploadFile, Long> {
 /*   public UploadFile findOneByFileName(String fileName);

    public UploadFile findOne(long fileId);*/
}
