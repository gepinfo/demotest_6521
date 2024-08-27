package com.geppetto.demotest.controller;

import com.geppetto.demotest.dto.EmpaDto;
import com.geppetto.demotest.service.EmpaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/empa")
public class EmpaController {

   private final EmpaService empaService;

   public EmpaController(EmpaService empaService) {
      this.empaService = empaService;
   }

    @PostMapping
    public ResponseEntity<EmpaDto> create(@RequestBody EmpaDto empaDto) {
      return ResponseEntity.status(HttpStatus.OK).body(empaService.create(empaDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmpaDto> getEntityById(@PathVariable String id) {
       return ResponseEntity.ok(empaService.getEntityById(id));
    }


    @GetMapping
     public ResponseEntity<List<EmpaDto>> getAllValues() {
       return ResponseEntity.ok(empaService.getAllValues());
     }


    @PutMapping
    public ResponseEntity<EmpaDto> update(@RequestBody EmpaDto empaDto) {
       return ResponseEntity.ok(empaService.update(empaDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
       return ResponseEntity.ok(empaService.delete(id));
    }


    @GetMapping("/search")
    public ResponseEntity<List<EmpaDto>> search(@RequestParam Map<String, String> allParams) {
       return ResponseEntity.ok(empaService.search(allParams));
    }
    

    @GetMapping("/searchUpdate")
    public ResponseEntity<EmpaDto> searchForUpdate(@RequestBody EmpaDto empaDto) {
       return ResponseEntity.ok(empaService.update(empaDto));
    }

}