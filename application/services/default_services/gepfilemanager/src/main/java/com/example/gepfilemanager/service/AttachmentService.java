package com.example.gepfilemanager.service;

import com.example.gepfilemanager.dao.AttachmentToDBDao;
import com.example.gepfilemanager.dao.AttachmentToFileSystemDao;
import com.example.gepfilemanager.dao.AttachmentToGCSDao;
import com.example.gepfilemanager.dto.AttachmentResponseDTO;
import com.example.gepfilemanager.dto.RequestDTO;
import com.example.gepfilemanager.model.Attachment;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class AttachmentService {

    private final AttachmentToGCSDao attachmentToGCSDao;
    private final AttachmentToDBDao attachmentToDBDao;
    private final AttachmentToFileSystemDao attachmentToFileSystemDao;

    @Value("${storage.type}")
    private String storageType;

    public String getStorageType() {
        return storageType;
    }

    public AttachmentService(AttachmentToGCSDao attachmentToGCSDao, AttachmentToDBDao attachmentToDBDao, AttachmentToFileSystemDao attachmentToFileSystemDao) {
        this.attachmentToGCSDao = attachmentToGCSDao;
        this.attachmentToDBDao = attachmentToDBDao;
        this.attachmentToFileSystemDao = attachmentToFileSystemDao;
    }

    public AttachmentResponseDTO addAttachment(RequestDTO requestDTO, MultipartFile file) throws IOException {
        if ("GCS".equals(getStorageType())) {
            attachmentToGCSDao.fileUploadToGcs(file);
        } else if ("FS".equals(storageType)) {
            attachmentToFileSystemDao.fileSaveToSystem(file,requestDTO.getFileKey());
        }
        attachmentToDBDao.addAttachment(requestDTO.getFileUrl(),file.getBytes(),requestDTO.getFileKey());
        return null;
    }

    public Object getAttachment(Long id) {
        log.info("Enter into AttachmentService: getAttachment");
        System.out.println("req.body " + id);

       Optional<Attachment> attachment= attachmentToDBDao.getAttachments(id);
       return attachment.get();

    }


    public AttachmentResponseDTO deleteAttachment(Long id) throws IOException {
        Attachment attachment= (Attachment) getAttachment(id);
        if ("GCS".equals(storageType)) {
            attachmentToGCSDao.deleteAttachment(attachment.getFileKey());
        } else if ("FS".equals(storageType)) {
            attachmentToFileSystemDao.deleteAttachment(attachment.getFileKey());
        }
        attachmentToDBDao.deleteAttachment(attachment.getFileKey());
        return null;
    }

    public byte[] downloadAttachment(Long id) throws IOException {
        Attachment attachment= (Attachment) getAttachment(id);
        if ("GCS".equals(storageType)) {
            return attachmentToGCSDao.fileDownloadFromGcs(attachment.getFileKey());
        } else if ("FS".equals(storageType)) {
            attachmentToFileSystemDao.fileDownload(attachment.getFileKey());
        }
        return null;
    }

    public AttachmentResponseDTO uploadS3(RequestDTO requestDTO, MultipartFile file, boolean thumbnail) throws IOException {
        if ("GCS".equals(storageType)) {
            byte[] fileBytes = file.getBytes();
            attachmentToGCSDao.fileUploadToGcs(file);
            if (thumbnail) {
                byte[] thumbnailBytes = generateThumbnail(fileBytes);
                String thumbnailFileKey = "thumbnailimages/" + requestDTO.getFileKey();
                attachmentToGCSDao.uploadGcs(thumbnailBytes, thumbnailFileKey);
            }
        } else if ("FS".equals(storageType)) {
            attachmentToFileSystemDao.fileSaveToSystem(file, requestDTO.getFileKey());
        }
        // Additional logic for database operations if needed
        return null;
    }

    private byte[] generateThumbnail(byte[] imageBytes) throws IOException {
        // Implement logic to generate thumbnail from imageBytes
        // You may use a library like Java Image I/O or Thumbnailator
        return imageBytes; // Placeholder, adjust as needed
    }
}
