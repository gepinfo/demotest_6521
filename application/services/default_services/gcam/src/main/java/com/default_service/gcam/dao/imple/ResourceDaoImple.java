package com.default_service.gcam.dao.imple;

import com.default_service.gcam.dao.ResourceDao;
import com.default_service.gcam.model.Resource;
import com.default_service.gcam.repositories.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ResourceDaoImple implements ResourceDao {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public void saveResource(Resource newResource) {
        log.info("Enter into saveResource:ResourceDaoImple");
        resourceRepository.save(newResource);
    }

    @Override
    public void deleteResource(Resource resource) {
        log.info("Enter into deleteResource:ResourceDaoImple");
        resourceRepository.delete(resource);
    }

    @Override
    public List<Resource> getAllResource() {
        log.info("Enter into getAllResource:ResourceDaoImple");
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> getResourceAuthorizationByRole(List<String> roles) {
        log.info("Enter into getResourceAuthorizationByRole:ResourceDaoImple");
        return resourceRepository.findByRolesIn(roles);
    }

    @Override
    public Resource getResourceById(String id) {
        log.info("Enter into getResourceById:ResourceDaoImple");
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        log.info("Enter into existsById:ResourceDaoImple");
        return resourceRepository.existsById(id);
    }

    @Override
    public Resource gcamUpdate(Resource resource) {
        log.info("Enter into gcamUpdate:ResourceDaoImple");
        return resourceRepository.save(resource);
    }

    @Override
    public void gcamDelete(String id) {
        log.info("Enter into gcamDelete:ResourceDaoImple");
        resourceRepository.deleteById(id);
    }

    @Override
    public Resource gcamGenerate(Map<String, Object> payload) {
        return null;
    }


}

