package com.example.gepfilemanager.dao;

import com.google.cloud.storage.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AttachmentToGCSDao {

    private final Storage gcs;
    private final String bucketName;

    public AttachmentToGCSDao(Storage gcs) {
        this.gcs = gcs;
        this.bucketName = "your-gcs-bucket-name";
    }

    public String fileUploadToGcs(MultipartFile file) throws IOException {
        String fileKey = generateFileKey(file.getOriginalFilename());

        BlobId blobId = BlobId.of(bucketName, fileKey);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        try (InputStream content = file.getInputStream()) {
            Blob blob = gcs.create(blobInfo, content);
            return fileKey;
        }
    }

    public void deleteAttachment(String fileKey) {
        BlobId blobId = BlobId.of(bucketName, fileKey);
        gcs.delete(blobId);
    }

    public byte[] fileDownloadFromGcs(String fileKey) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileKey);
        Blob blob = gcs.get(blobId);
        return blob.getContent();
    }

    public String uploadGcs(byte[] data, String fileKey) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileKey);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        try {
            Blob blob = gcs.create(blobInfo, data);
            System.out.println("File uploaded successfully to GCS with key: " + fileKey);
            return "File uploaded successfully to GCS with key: " + fileKey;
        } catch (Exception e) {
            System.err.println("Error uploading file to GCS: " + e.getMessage());
            throw new IOException("Error uploading file to GCS: " + e.getMessage(), e);
        }
    }

    private String generateFileKey(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
}


