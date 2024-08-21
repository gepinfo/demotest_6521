package com.example.systemcredentialmanager.service.serviceImpl;

import com.example.systemcredentialmanager.dao.SystemCredentialManagerVaultDao;
import com.example.systemcredentialmanager.dao.SystemCredentialsManagerDBDao;
import com.example.systemcredentialmanager.dto.SystemCredentialExtDto;
import com.example.systemcredentialmanager.dto.SystemCredentialExtUpdateDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultUpdateDto;
import com.example.systemcredentialmanager.model.SystemCredentialsExternalManager;
import com.example.systemcredentialmanager.model.SystemCredentialsVaultManager;
import com.example.systemcredentialmanager.responseDto.Response;
import com.example.systemcredentialmanager.responseDto.SystemCredentialExtResDto;
import com.example.systemcredentialmanager.service.SystemCredentialsManagerService;
import com.example.systemcredentialmanager.utility.JsonUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.support.VaultResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class SystemCredentialsManagerServiceImpl implements SystemCredentialsManagerService {

    @Autowired
    private SystemCredentialManagerVaultDao systemCredentialsManagerVaultDao;

    @Autowired
    private SystemCredentialsManagerDBDao systemCredentialsManagerDBDao;



    public Object GpSearch(String vaultName) {
        VaultResponse response =systemCredentialsManagerVaultDao.GpVaultGet(vaultName);

        return response.getData();
    }


    public Object GpUpdate(SystemCredentialVaultUpdateDto systemCredentialVaultUpdateDto, String vaultName) throws Exception {
        try{
        SystemCredentialsVaultManager systemCredentialsVaultManager=new SystemCredentialsVaultManager();
        Object vaultResponse =GpGetVaultByName(vaultName);
        String jsonResponse = JsonUtils.objectToJson(vaultResponse);
        JsonObject jsonObject = JsonUtils.stringToJsonObject(jsonResponse);
        SystemCredentialsVaultManager response = JsonUtils.jsonObjectToObject(jsonObject, SystemCredentialsVaultManager.class);
        BeanUtils.copyProperties(response,systemCredentialsVaultManager);
        systemCredentialsVaultManager.setLastModifiedBy(systemCredentialVaultUpdateDto.getModifiedBy());
        systemCredentialsVaultManager.setCredentials(systemCredentialVaultUpdateDto.getCredentials());
        systemCredentialsVaultManager.setLastModifiedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
           systemCredentialsManagerVaultDao.GpVaultUpdate(systemCredentialsVaultManager,vaultName);
            return systemCredentialsVaultManager;
    }catch(Exception e){Response response =new Response();
        response.setResponseMsg("vault not exists");
        return response;}
    }




    public Object GpGetAllValues() throws Exception {

        List<String> paths  = systemCredentialsManagerVaultDao.GpVaultGetallPath();
        if (!paths.isEmpty()) {
            List<SystemCredentialsVaultManager> systemCredentialsVaultManagerList=new ArrayList<>();
            for (String path : paths) {
                SystemCredentialsVaultManager systemCredentialsVaultManager = new SystemCredentialsVaultManager();
                VaultResponse VaultResponse = systemCredentialsManagerVaultDao.GpVaultGetByVaultName(path);
                String jsonResponse = JsonUtils.objectToJson(VaultResponse.getData());
                JsonObject jsonObject = JsonUtils.stringToJsonObject(jsonResponse);
                SystemCredentialsVaultManager response = JsonUtils.jsonObjectToObject(jsonObject, SystemCredentialsVaultManager.class);
                BeanUtils.copyProperties(response,systemCredentialsVaultManager);
                systemCredentialsVaultManagerList.add(systemCredentialsVaultManager);
            }
            return systemCredentialsVaultManagerList;
        }else{
            Response response = new Response();
            response.setResponseMsg("credential Not found");
            return response;
        }

    }


    public Object GpGetVaultByName(String vaultName) {

        VaultResponse response = systemCredentialsManagerVaultDao.GpVaultGetByVaultName(vaultName);
        return response.getData();
    }


    public Object GpVaultGetallPath() {

           return systemCredentialsManagerVaultDao.GpVaultGetallPath();


    }


    public Object GpDelete(String vaultName) {
        Response response = new Response();
            try {
                systemCredentialsManagerVaultDao.GpVaultDelete(vaultName);
                response.setResponseMsg("systemCredentials have deleted successfully");
                return response;
            }catch (Exception e){
                response.setResponseMsg("systemCredentials is not get deleted");
                return response;
            }
    }


    public Object GpCreate(SystemCredentialVaultDto systemCredentialVaultDto, String vaultName) throws Exception {
        VaultResponse vaultResponse = systemCredentialsManagerVaultDao.GpVaultGetByVaultName(vaultName);
        if(!(vaultResponse==null)) {
            String jsonResponse = JsonUtils.objectToJson(vaultResponse.getData());
            JsonObject jsonObject = JsonUtils.stringToJsonObject(jsonResponse);
            SystemCredentialsVaultManager response = JsonUtils.jsonObjectToObject(jsonObject, SystemCredentialsVaultManager.class);
            if(!(response.getUserId().equalsIgnoreCase(systemCredentialVaultDto.getUserId()))) {
                return vaultCreate(systemCredentialVaultDto,vaultName);
            } else {
                Response userResponse = new Response();
                userResponse.setResponseMsg("userId is already exists");
                return userResponse;
            }
        }else{
         return vaultCreate(systemCredentialVaultDto,vaultName);
        }
    }
    public Object vaultCreate(SystemCredentialVaultDto systemCredentialVaultDto, String vaultName){
        try{
        SystemCredentialsVaultManager systemCredentialsVaultManager = new SystemCredentialsVaultManager();
        BeanUtils.copyProperties(systemCredentialVaultDto, systemCredentialsVaultManager);
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        systemCredentialsVaultManager.setCreatedDate(formattedDateTime);
        systemCredentialsManagerVaultDao.GpVaultCreate(systemCredentialsVaultManager, vaultName);
        return systemCredentialsVaultManager;
     }catch (Exception e){
        Response userResponse = new Response();
        userResponse.setResponseMsg("userId is already exists");
        return userResponse;
    }
    }
    public Object GpExternalAll() {

            Object response = systemCredentialsManagerDBDao.GpExternalVaultAll();
            return response;
    }


    public Object GpExternaladd(SystemCredentialExtDto systemCredentialExtDto) {
        SystemCredentialsExternalManager systemCredentialsExternalManager = new SystemCredentialsExternalManager();
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        systemCredentialsExternalManager.setCreatedDate(formattedDateTime);
        BeanUtils.copyProperties(systemCredentialExtDto,systemCredentialsExternalManager);
        if(GpExternalById(systemCredentialsExternalManager.getUserId())==null) {
            Object response = systemCredentialsManagerDBDao.GpExternalVaultAdd(systemCredentialsExternalManager);
            SystemCredentialExtResDto systemCredentialExtResDto = new SystemCredentialExtResDto();
            BeanUtils.copyProperties(response, systemCredentialExtResDto);
            return response;
        }else{
            Response response = new Response();
            response.setResponseMsg("userId is already exists");
            return response;
        }
    }


    public Object GpExternalDelete(String id) {
        Response response = new Response();
        try {
            systemCredentialsManagerDBDao.GpExternalVaultDelete(id);
            response.setResponseMsg("systemCredentials have deleted successfully");
            return response;
         }catch (Exception e){
        response.setResponseMsg("systemCredentials is not get deleted");
        return response;
    }
    }


    public Object GpExternalUpdate(SystemCredentialExtUpdateDto systemCredentialExtUpdateDto) {
        try {
            SystemCredentialsExternalManager systemCredentialsExternalManager = (SystemCredentialsExternalManager) GpExternalById(systemCredentialExtUpdateDto.getUserId());
        BeanUtils.copyProperties(systemCredentialExtUpdateDto,systemCredentialsExternalManager);
        systemCredentialsExternalManager.setLastModifiedBy(systemCredentialExtUpdateDto.getModifiedBy());
        systemCredentialsExternalManager.setCredentials(systemCredentialExtUpdateDto.getCredentials());
        systemCredentialsExternalManager.setLastModifiedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Object response = systemCredentialsManagerDBDao.GpExternalVaultUpdate(systemCredentialsExternalManager);
        SystemCredentialExtResDto systemCredentialExtResDto = new SystemCredentialExtResDto();
        BeanUtils.copyProperties(response,systemCredentialExtResDto);
            return systemCredentialExtResDto;
        }catch(Exception e){Response response =new Response();
            response.setResponseMsg("userId not exists");
            return response;}
    }

    @Override
    public Object GpExternalById(String userId) {
        SystemCredentialsExternalManager systemCredentialsExternalManager= systemCredentialsManagerDBDao.GpExternalById(userId);
            return systemCredentialsExternalManager;

    }
}

