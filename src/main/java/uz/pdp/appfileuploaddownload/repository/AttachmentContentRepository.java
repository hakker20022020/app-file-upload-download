package uz.pdp.appfileuploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfileuploaddownload.entity.Attachment;
import uz.pdp.appfileuploaddownload.entity.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
}
