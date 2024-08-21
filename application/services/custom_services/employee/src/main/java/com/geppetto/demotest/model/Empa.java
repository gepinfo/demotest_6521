package com.geppetto.demotest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

import jakarta.persistence.*;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empa {

    @Id
    private String _id;
    
    private String empname;
    
    private Number age;
    
    
    }

