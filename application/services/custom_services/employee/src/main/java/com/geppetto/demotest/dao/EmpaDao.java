package com.geppetto.demotest.dao;

import com.geppetto.demotest.model.Empa;
import java.util.List;
import java.util.Optional;


public interface EmpaDao {

    Empa create(Empa empa);




    Optional<Empa> update(String id);


    void delete(String id);

}
