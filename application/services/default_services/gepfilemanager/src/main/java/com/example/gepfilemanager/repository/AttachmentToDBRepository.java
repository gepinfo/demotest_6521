package com.example.gepfilemanager.repository;

import com.example.gepfilemanager.model.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentToDBRepository extends MongoRepository<Attachment,Long> {
    void deleteByFileKey(String fileKey);
}
