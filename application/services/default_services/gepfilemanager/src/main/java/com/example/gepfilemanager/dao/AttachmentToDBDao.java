package com.example.gepfilemanager.dao;

import com.example.gepfilemanager.model.Attachment;
import com.example.gepfilemanager.repository.AttachmentToDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AttachmentToDBDao {

    private final AttachmentToDBRepository attachmentRepository;

    @Autowired
    public AttachmentToDBDao(AttachmentToDBRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public void addAttachment(String url, byte[] data, String fileKey) {
        Attachment attachment = new Attachment();
        attachment.setFileKey(fileKey);
        attachment.setFileData(data);
        attachment.setFileUrl(url);
        attachment.setResource("your-resource-name");
        attachment.setCreatedOn(new Date());

        attachmentRepository.save(attachment);
    }

    public Optional<Attachment> getAttachments(Long fileIds) {
        return attachmentRepository.findById(fileIds);
    }

    public void deleteAttachment(String fileKey) {
        attachmentRepository.deleteByFileKey(fileKey);
    }
}

