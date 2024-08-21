package com.geppetto.demotest.dao.daoimpl;

import com.geppetto.demotest.model.Empa;
import com.geppetto.demotest.repository.EmpaRepository;
import com.geppetto.demotest.dao.EmpaDao;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmpaDaoImpl implements EmpaDao{

    private final EmpaRepository empaRepository;

     public EmpaDaoImpl(EmpaRepository empaRepository) {
       this.empaRepository = empaRepository;
}

    @Override
    public Empa create(Empa empa) {
      return empaRepository.save(empa);
    }




    @Override
    public Optional<Empa> update(String id) {
      return empaRepository.findById(id);
    }


    @Override
    public void delete(String id) {
      empaRepository.deleteById(id);
    }

}
