package com.example.authproxy.dao.daoImpl;

import com.example.authproxy.dao.ProxyDao;
import com.example.authproxy.dto.RoleRequest;
import com.example.authproxy.dto.UserDetails;
import com.example.authproxy.service.serviceImpl.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@Slf4j
public class ProxyDaoImpl implements ProxyDao {
    private final WebClient webClient;
    private final String gCamUrl;

    public ProxyDaoImpl(@Value("${gCamUrl}") String gCamUrl, WebClient.Builder webClientBuilder) {
        this.gCamUrl = gCamUrl;
        this.webClient = webClientBuilder.baseUrl(gCamUrl).build();
    }

    public String userDao(UserDetails userDetails) {
        try {
            log.info("Enter into ProxyDao.java: userDao");
            List<String> role = userDetails.getListOfRoles();
            String postUrl = gCamUrl + "/gaccesslevel";

            RoleRequest roleRequest = new RoleRequest();
            roleRequest.setListOfRoles(role);
            String jsonResponse = JsonUtils.objectToJson(roleRequest);
            String response = webClient.post()
                    .uri(postUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(jsonResponse))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}

