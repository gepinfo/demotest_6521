package com.example.systemcredentialmanager.service;


import com.example.systemcredentialmanager.dto.SystemCredentialExtDto;
import com.example.systemcredentialmanager.dto.SystemCredentialExtUpdateDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultUpdateDto;

public interface SystemCredentialsManagerService {

    Object GpSearch(String vaultName);
    Object GpGetAllValues() throws Exception;
    Object GpGetVaultByName(String vaultName);
    Object GpVaultGetallPath();
    Object GpDelete(String vaultName);
    Object GpCreate(SystemCredentialVaultDto systemCredentialVaultDto, String vaultName) throws Exception;
    Object GpExternalAll();
    Object GpExternaladd(SystemCredentialExtDto systemCredentialMnDto);
    Object GpExternalDelete(String userID);
    Object GpExternalUpdate(SystemCredentialExtUpdateDto systemCredentialExtUpdateDto);

    Object GpUpdate(SystemCredentialVaultUpdateDto systemCredentialVaultUpdateDto, String userId) throws Exception;

    Object GpExternalById(String userId);
}
