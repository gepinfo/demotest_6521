package com.example.gepfilemanager.controller;

import com.example.gepfilemanager.dto.AttachmentResponseDTO;
import com.example.gepfilemanager.dto.RequestDTO;
import com.example.gepfilemanager.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/attachments")
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addAttachment(@RequestBody RequestDTO requestDTO,@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Enter into AttachmentController.java: addAttachment");
        // Assuming RequestDTO is a class representing your request payload
        AttachmentResponseDTO response = attachmentService.addAttachment(requestDTO,file);
        log.info("Exit from AttachmentController.java: addAttachment");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getAttachment(@PathVariable Long id) {
        log.info( "Enter into AttachmentController.java: getAttachment");
        AttachmentResponseDTO response = (AttachmentResponseDTO) attachmentService.getAttachment(id);
        log.info( "Exit from AttachmentController.java: getAttachment");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAttachment(@PathVariable Long id) throws IOException {
        log.info( "Enter into AttachmentController.java: deleteAttachment");
        AttachmentResponseDTO response = attachmentService.deleteAttachment(id);
        log.info( "Exit from AttachmentController.java: deleteAttachment");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Object> downloadAttachment(@PathVariable Long id) throws IOException {
        log.info( "Enter into AttachmentController.java: downloadAttachment");
        byte[] response = attachmentService.downloadAttachment(id);
        log.info( "Exit from AttachmentController.java: downloadAttachment");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/uploadS3")
    public ResponseEntity<Object> uploadS3(@RequestBody RequestDTO requestDTO,@RequestParam("file") MultipartFile file) throws IOException {
        log.info( "Enter into AttachmentController.java: uploadS3");
        AttachmentResponseDTO response = attachmentService.uploadS3(requestDTO,file,true);
        log.info( "Exit from AttachmentController.java: uploadS3");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

