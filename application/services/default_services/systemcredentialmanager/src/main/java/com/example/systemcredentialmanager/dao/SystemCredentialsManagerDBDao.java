package com.example.systemcredentialmanager.dao;

import com.example.systemcredentialmanager.model.SystemCredentialsExternalManager;

import java.util.List;
import java.util.Optional;

public interface SystemCredentialsManagerDBDao {

    List<SystemCredentialsExternalManager> GpExternalVaultAll();
    SystemCredentialsExternalManager GpExternalVaultAdd(SystemCredentialsExternalManager systemCredentialsExternalManager);
    SystemCredentialsExternalManager GpExternalVaultUpdate(SystemCredentialsExternalManager systemCredentialsExternalManager);
    void GpExternalVaultDelete(String userId);


    SystemCredentialsExternalManager GpExternalById(String userId);
}
