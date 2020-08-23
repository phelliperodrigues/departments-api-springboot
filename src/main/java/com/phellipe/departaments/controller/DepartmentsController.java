package com.phellipe.departaments.controller;
import com.phellipe.departaments.dto.DepartmentsDTO;
import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.service.DepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/departments")
@Api("Department API")
@AllArgsConstructor
@Slf4j
public class DepartmentsController {

    private final DepartmentsService service;
    private final ModelMapper mapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a Department")
    public DepartmentsDTO create(@RequestBody DepartmentsDTO dto){
        log.info(" creating a department: {} ", dto.getName());
        Department entity = mapper.map( dto, Department.class );
        entity = service.save(entity);
        return mapper.map(entity, DepartmentsDTO.class);
    }
}
