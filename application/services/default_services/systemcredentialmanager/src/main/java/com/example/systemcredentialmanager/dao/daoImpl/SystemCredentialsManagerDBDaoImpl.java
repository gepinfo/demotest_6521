package com.example.systemcredentialmanager.dao.daoImpl;

import com.example.systemcredentialmanager.dao.SystemCredentialsManagerDBDao;
import com.example.systemcredentialmanager.model.SystemCredentialsExternalManager;
import com.example.systemcredentialmanager.repository.SystemCredentialsManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class SystemCredentialsManagerDBDaoImpl implements SystemCredentialsManagerDBDao {

    @Autowired
    private SystemCredentialsManagerRepository systemCredentialsManagerRepository;



    public List<SystemCredentialsExternalManager> GpExternalVaultAll() {
        log.info("Enter into SystemCredentialsManagerDBDao: GpExternalVaultAll");
        return systemCredentialsManagerRepository.findAll();



    }

    public SystemCredentialsExternalManager GpExternalVaultAdd(SystemCredentialsExternalManager systemCredentialsExternalManager) {
        log.info("Enter into SystemCredentialsManagerDBDao: GpExternalVaultadd");
        return systemCredentialsManagerRepository.save(systemCredentialsExternalManager);




    }

    public SystemCredentialsExternalManager GpExternalVaultUpdate(SystemCredentialsExternalManager systemCredentialsExternalManager) {
        log.info("Enter into SystemCredentialsManagerDBDao: GpExternalVaultUpdate");
        return systemCredentialsManagerRepository.save(systemCredentialsExternalManager);


    }

    public void GpExternalVaultDelete(String userId) {
        log.info("Enter into SystemCredentialsManagerDBDao: GpExternalVaultDelete");
        systemCredentialsManagerRepository.deleteByUserId(userId);


    }

    @Override
    public SystemCredentialsExternalManager GpExternalById(String userId) {
      return  systemCredentialsManagerRepository.findByUserId(userId);
    }

}
