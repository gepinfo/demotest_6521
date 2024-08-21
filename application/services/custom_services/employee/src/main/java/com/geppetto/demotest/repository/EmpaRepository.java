package com.geppetto.demotest.repository;

import com.geppetto.demotest.model.Empa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpaRepository extends MongoRepository<Empa,String>  {

}
