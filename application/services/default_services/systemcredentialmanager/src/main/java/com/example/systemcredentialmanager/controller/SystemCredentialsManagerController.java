package com.example.systemcredentialmanager.controller;

import com.example.systemcredentialmanager.dto.SystemCredentialExtDto;
import com.example.systemcredentialmanager.dto.SystemCredentialExtUpdateDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultDto;
import com.example.systemcredentialmanager.dto.SystemCredentialVaultUpdateDto;
import com.example.systemcredentialmanager.service.SystemCredentialsManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scm")
@Slf4j
public class SystemCredentialsManagerController {

    @Autowired
    private SystemCredentialsManagerService systemCredentialsManagerService;

    @GetMapping("/search")
    public ResponseEntity<Object> GpSearch(@RequestParam String vaultName) {
        try {
            Object response =systemCredentialsManagerService.GpSearch(vaultName);
            log.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> GpUpdate(@RequestBody SystemCredentialVaultUpdateDto systemCredentialVaultUpdateDto, @RequestParam String vaultName) {
        try {
            Object response = systemCredentialsManagerService.GpUpdate(systemCredentialVaultUpdateDto,vaultName);
            log.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> GpGetAllValues() {
        try {
            Object response = systemCredentialsManagerService.GpGetAllValues();
            log.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/byname")
    public ResponseEntity<Object> GpGetVaultByName(@RequestParam String vaultName) {
        try {
            Object response = systemCredentialsManagerService.GpGetVaultByName(vaultName);
            log.info("-----{}", response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> GpVaultGetAllPath() {
        try {
            Object response = systemCredentialsManagerService.GpVaultGetallPath();
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{vaultName}")
    public ResponseEntity<Object> GpDelete(@PathVariable String vaultName) {
        try {
            Object response = systemCredentialsManagerService.GpDelete(vaultName);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> GpCreate(@RequestParam String vaultName,@RequestBody SystemCredentialVaultDto systemCredentialVaultDto) {
        try {
            Object response = systemCredentialsManagerService.GpCreate(systemCredentialVaultDto,vaultName);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/external")
    public ResponseEntity<Object> GpExternalAll() {
        try {
            Object response = systemCredentialsManagerService.GpExternalAll();
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping("/external")
    public ResponseEntity<Object> GpExternalAdd(@RequestBody SystemCredentialExtDto systemCredentialExtDto) {
        try {
            Object response = systemCredentialsManagerService.GpExternaladd(systemCredentialExtDto);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/external/{userId}")
    public ResponseEntity<Object> GpExternalDelete(@PathVariable String userId) {
        try {
            Object response = systemCredentialsManagerService.GpExternalDelete(userId);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PutMapping("/external/update")
    public ResponseEntity<Object> GpExternalUpdate(@RequestBody SystemCredentialExtUpdateDto systemCredentialExtUpdateDto) {
        try {
            Object response = systemCredentialsManagerService.GpExternalUpdate(systemCredentialExtUpdateDto);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/external-get/{userId}")
    public ResponseEntity<Object> GpExternalById(@RequestParam String userId){
        try {
            Object response = systemCredentialsManagerService.GpExternalById(userId);
            log.info("-----{}", response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

}

