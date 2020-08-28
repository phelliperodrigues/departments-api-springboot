package com.phellipe.departaments.controller;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.phellipe.departaments.controller.exception.ApiExceptions;
import com.phellipe.departaments.controller.exception.BusinessException;
import com.phellipe.departaments.dto.DepartmentsDTO;
import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.service.DepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public DepartmentsDTO create(@Valid @RequestBody DepartmentsDTO dto){
        log.info(" creating a department: {} ", dto.getName());
        Department entity = mapper.map( dto, Department.class );
        entity = service.save(entity);
        return mapper.map(entity, DepartmentsDTO.class);
    }

    @GetMapping("/all")
    @ApiOperation("Find All Departments")
    public List<DepartmentsDTO> get() {
        log.info(" obtaining all departments");

        List<Department> result = service.findAll();
        return result
                .stream()
                .map(entity -> mapper.map(entity, DepartmentsDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ApiOperation("Get a departments details by id")
    public DepartmentsDTO get( @PathVariable Long id ){
        log.info(" obtaining details for departments id: {} ", id);
        return service
                .getById(id)
                .map( department -> mapper.map(department, DepartmentsDTO.class)  )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @GetMapping
    @ApiOperation("Lists departments by params")
    public Page<DepartmentsDTO> find( DepartmentsDTO dto, Pageable pageRequest ){
        Department filter = mapper.map(dto, Department.class);
        Page<Department> result = service.find(filter, pageRequest);
        List<DepartmentsDTO> list = result.getContent()
                .stream()
                .map(entity -> mapper.map(entity, DepartmentsDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<DepartmentsDTO>( list, pageRequest, result.getTotalElements() );
    }

    @PutMapping("{id}")
    @ApiOperation("Updates a department")
    public DepartmentsDTO update( @PathVariable Long id, @RequestBody @Valid DepartmentsDTO dto){
        log.info(" updating department of id: {} ", id);
        return service.getById(id).map( department -> {
            department.setName(dto.getName());
            department.setRegion(dto.getRegion());
            department.setCity(dto.getCity());
            department.setState(dto.getState());
            department.setBoardDirector(dto.getBoardDirector());
            department = service.update(department);
            return mapper.map(department, DepartmentsDTO.class);

        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletes a department by id")
    public void delete(@PathVariable Long id){
        log.info(" deleting department of id: {} ", id);
        Department department = service.getById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND) );
        service.delete(department);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiExceptions(bindingResult);
    }

   }
