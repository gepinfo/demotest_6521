package com.example.systemcredentialmanager.dao.daoImpl;



import com.example.systemcredentialmanager.dao.SystemCredentialManagerVaultDao;
import com.example.systemcredentialmanager.model.SystemCredentialsVaultManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SystemCredentialsManagerVaultDaoImpl implements SystemCredentialManagerVaultDao {


    @Autowired
    private VaultTemplate vaultTemplate;


    public VaultResponse GpVaultGetAll() {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpReadAll");
          VaultResponse vaultResponse=vaultTemplate.read("kv");
          return vaultResponse;
    }

    public List<String> GpVaultGetallPath() {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpListAll");
        return vaultTemplate.list("kv");

    }

    public VaultResponse GpVaultGetByVaultName(String vaultName) {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpReadAll");
        return vaultTemplate.read("kv/" + vaultName);


    }



    public VaultResponse GpVaultCreate(SystemCredentialsVaultManager systemCredentialsVaultManager, String vaultName) {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpCreate");
        Map<String,Object> map=new HashMap<>();
        VaultResponse response = vaultTemplate.write("kv/"+vaultName,systemCredentialsVaultManager);
            log.info("Exit from SystemCredentialsManagerVaultDao: GpCreate");
            log.info("Credentials Store into the Vault");
            return response;
    }

    public VaultResponse GpVaultUpdate(SystemCredentialsVaultManager systemCredentialsVaultManager, String vaultName) {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpUpdate");
        Map<String,Object> map=new HashMap<>();
       return vaultTemplate.write("kv/" + vaultName, systemCredentialsVaultManager);


    }

    public VaultResponse GpVaultGet(String vaultName) {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpRead");
        return vaultTemplate.read("kv/" + vaultName);

    }

    public void GpVaultDelete(String vaultName) {
        log.info("Enter into SystemCredentialsManagerVaultDao: GpDelete");
        vaultTemplate.delete("kv/" + vaultName);
            log.info("Exit from SystemCredentialsManagerVaultDao: GpDelete");
            log.info("Credentials Data Deleted into the Vault");
    }



}
