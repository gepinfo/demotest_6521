package com.geppetto.demotest.apiAdapter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;


@Component
public class ApiAdapter {

    private  final RestTemplate restTemplate = new RestTemplate();

    public   ResponseEntity<Object>  post(String url, Object body, HttpServletRequest request) throws Exception {
        String endPointUrl=url;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            endPointUrl=endPointUrl+"?"+paramName+"="+paramValue;
        }

        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(endPointUrl, HttpMethod.POST, requestEntity, Object.class);

        return responseEntity;
    }

    public  ResponseEntity<Object> get(String url,HttpServletRequest request) throws Exception {
        String endPointUrl=url;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            endPointUrl=endPointUrl+"?"+paramName+"="+paramValue;
        }
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(endPointUrl, Object.class);

        return responseEntity;
    }

    public  ResponseEntity<Object> put(String url, Object body, HttpServletRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String endPointUrl=url;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            endPointUrl=endPointUrl+"?"+paramName+"="+paramValue;
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(endPointUrl, HttpMethod.PUT, requestEntity, Object.class);

        return responseEntity;
    }

    public  ResponseEntity<Object> delete(String url, HttpServletRequest request) throws Exception {
        String endPointUrl=url;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            endPointUrl=endPointUrl+"?"+paramName+"="+paramValue;
        }
        ResponseEntity<Object> responseEntity = restTemplate.exchange(endPointUrl, HttpMethod.DELETE, null, Object.class);

        return responseEntity;
    }


}