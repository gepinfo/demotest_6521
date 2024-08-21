package com.default_service.gcam.controllers;

import com.default_service.gcam.dto.RequestDto;
import com.default_service.gcam.dto.ResourceRequestDto;
import com.default_service.gcam.dto.ResourceResponseDto;
import com.default_service.gcam.service.GcamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class GcamController {

    @Autowired
    GcamService gcamService;

    @PostMapping("gaccesslevel")
    public ResponseEntity<String> getResourceAuthorizationsByRole(@RequestBody RequestDto requestDto) {
        log.info("Enter into getResourceAuthorizationsByRole:Gcam ");
        return gcamService.getResourceAuthorizationByRole(requestDto);
    }

    @GetMapping("gcamallscreens")
    public ResponseEntity<List<ResourceResponseDto>> getAllResource() {
        log.info("Enter into getAllResource:Gcam ");
        return gcamService.getAllResource();
    }

    @GetMapping("gcambyid/{id}")
    public ResponseEntity<ResourceResponseDto> getResourceById(@PathVariable String id) {
        log.info("Enter into getResourceById:Gcam ");
        return gcamService.getResourceById(id);
    }

    @PutMapping("gcamupdate")
    public ResponseEntity<ResourceResponseDto> updateResource(@RequestBody ResourceRequestDto requestDto) {
        log.info("Enter into updateResource:Gcam ");
        return gcamService.updateResource(requestDto);
    }

    @DeleteMapping("/gcamdeletebyid/{id}")
    public void deleteResource(@PathVariable String id) {
        log.info("Enter into deleteResource:Gcam ");
        gcamService.deleteResource(id);
    }

    @PostMapping("gcamgenerate")
    public ResponseEntity<Map<String, Object>> gcamGenerate(@RequestBody Map<String, Object> request) {
        log.info("Enter into gcamGenerate:Gcam ");
        return gcamService.GcamGgenerate(request);
    }
}


