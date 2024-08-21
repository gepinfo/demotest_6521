package com.example.authproxy.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Gson gson = new Gson();

    public static String objectToJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String jsonString, Class<T> valueType) throws Exception {
        return objectMapper.readValue(jsonString, valueType);
    }

    public static JsonObject stringToJsonObject(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public static <T> T jsonObjectToObject(JsonObject jsonObject, Class<T> valueType) {
        return gson.fromJson(jsonObject, valueType);
    }
}

