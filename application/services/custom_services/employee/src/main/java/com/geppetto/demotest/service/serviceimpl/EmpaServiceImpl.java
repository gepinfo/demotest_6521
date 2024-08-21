package com.geppetto.demotest.service.serviceimpl;

import com.geppetto.demotest.dao.EmpaDao;
import com.geppetto.demotest.model.Empa;
import com.geppetto.demotest.dto.EmpaDto;
import com.geppetto.demotest.service.EmpaService;
import com.geppetto.demotest.exception.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmpaServiceImpl implements EmpaService{

      private final EmpaDao empaDao;

      private final MongoTemplate mongoTemplate;
      public EmpaServiceImpl(EmpaDao  empaDao,MongoTemplate mongoTemplate) {
          this. empaDao =  empaDao;
          this.mongoTemplate=mongoTemplate;
}

    @Override
    public EmpaDto create(EmpaDto empaDto){
        Empa empa = new Empa();
        BeanUtils.copyProperties(empaDto, empa);
        Empa createdEmpa= empaDao.create(empa);
        BeanUtils.copyProperties(createdEmpa, empaDto);
        return empaDto;
    }




    @Override
    public EmpaDto update(EmpaDto empaDto) {
    Optional<Empa> result = empaDao.update(empaDto.get_id());
        if (result.isPresent()) {
           Empa empa = result.get();
           BeanUtils.copyProperties(empaDto, empa);
           empaDao.create(empa);
           return empaDto;
        } else {
        throw new EntityNotFoundException("Data not found for update with ID: " + empaDto.get_id());
        }
    }


    @Override
    public String delete(String id) {
    Optional<Empa> result = empaDao.getEntityById(id);
        if (result.isPresent()) {
            empaDao.delete(id);
        }else{
        throw new EntityNotFoundException("No entry found with ID: " + id + ". Unable to delete.");
        }
        return "Data Deleted Successfully";
    }


    @Override
    public List<EmpaDto> search(Map<String, String> allParams) {
        Query query = new BasicQuery(constructQuery(allParams));
        List<Empa> empaEntities= mongoTemplate.find(query, Empa.class);
        List<EmpaDto> empaDtoList = new ArrayList<>();
        for (Empa empaEntity : empaEntities) {
             EmpaDto empaDto = new EmpaDto();
             BeanUtils.copyProperties(empaEntity, empaDto);
             empaDtoList.add(empaDto);
            }
        return empaDtoList;
    }


    private String constructQuery(Map<String, String> params) {
       StringBuilder queryBuilder = new StringBuilder("{ ");
         for (Map.Entry<String, String> entry : params.entrySet()) {
         queryBuilder.append(entry.getKey()).append(": '").append(entry.getValue()).append("', ");
    }
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length()).append(" }");
        return queryBuilder.toString();
    }
}