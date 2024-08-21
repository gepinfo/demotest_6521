package com.default_service.gcam.service;

import com.default_service.gcam.dto.RequestDto;
import com.default_service.gcam.dto.ResourceRequestDto;
import com.default_service.gcam.dto.ResourceResponseDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GcamService {

    void saveResourceAndScreensFromJson(String filesPath)throws IOException;

    ResponseEntity<List<ResourceResponseDto>> getAllResource();

    ResponseEntity<List<ResourceResponseDto>> getResourceAuthorizationByRole(RequestDto requestDto);

    ResponseEntity<ResourceResponseDto> getResourceById(String id);

    ResponseEntity<ResourceResponseDto> updateResource(ResourceRequestDto requestDto);

    void deleteResource(String id);

    ResponseEntity<Map<String, Object>> GcamGgenerate(Map<String, Object> payload);
}
