package uz.pdp.appfileuploaddownload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appfileuploaddownload.entity.Attachment;
import uz.pdp.appfileuploaddownload.entity.AttachmentContent;
import uz.pdp.appfileuploaddownload.repository.AttachmentContentRepository;
import uz.pdp.appfileuploaddownload.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController{

    private AttachmentRepository attachmentRepository;

    private AttachmentContentRepository attachmentContentRepository;

    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return "File saved. Id is : " + savedAttachment.getId();
        }

        return "Xatolik";
    }

}
