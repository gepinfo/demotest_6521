package com.default_service.gcam.dao;

import com.default_service.gcam.model.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceDao {

    List<Resource> getAllResource();

    List<Resource> getResourceAuthorizationByRole(List<String> requestDto);

    Resource getResourceById(String id);

    boolean existsById(String id);

    Resource gcamUpdate(Resource resource1);

    void gcamDelete(String id);

    Resource gcamGenerate(Map<String, Object> payload);

    void saveResource(Resource newResource);

    void deleteResource(Resource resource);
}
