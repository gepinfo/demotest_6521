package com.example.systemcredentialmanager.dao;


import com.example.systemcredentialmanager.model.SystemCredentialsExternalManager;
import com.example.systemcredentialmanager.model.SystemCredentialsVaultManager;
import org.springframework.vault.support.VaultResponse;

import java.util.List;

public interface SystemCredentialManagerVaultDao {
    VaultResponse GpVaultGetAll();
    VaultResponse GpVaultGetByVaultName(String vaultName);
    List<String> GpVaultGetallPath();
    VaultResponse GpVaultCreate(SystemCredentialsVaultManager systemCredentialsVaultManager, String vaultName);
    VaultResponse GpVaultUpdate(SystemCredentialsVaultManager systemCredentialsVaultManager, String vaultName);
    VaultResponse GpVaultGet(String vaultName);
    void GpVaultDelete(String userID);

}
