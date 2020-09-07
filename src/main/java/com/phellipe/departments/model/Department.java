package com.phellipe.departments.model;

import com.phellipe.departments.model.enums.BoardDirector;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private String  region;
    private String  city;
    private String  state;
    private BoardDirector boardDirector;
}
