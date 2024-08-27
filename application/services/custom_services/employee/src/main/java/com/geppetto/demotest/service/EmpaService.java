package com.geppetto.demotest.service;

import com.geppetto.demotest.dto.EmpaDto;
import java.util.List;
import java.util.Map;

public interface EmpaService {

    EmpaDto create(EmpaDto empaDto);


    EmpaDto getEntityById(String id);


    List<EmpaDto> getAllValues();


    EmpaDto update(EmpaDto empaDto);


    String delete(String id);


    List<EmpaDto> search(Map<String, String> allParams);


}
