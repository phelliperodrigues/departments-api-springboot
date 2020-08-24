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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public DepartmentsDTO create(@Valid @RequestBody DepartmentsDTO dto){
        log.info(" creating a department: {} ", dto.getName());
        log.info(" creating a department: {} ", dto.getBoardDirector());
        log.info(" creating a department: {} ", dto.getCity());
        log.info(" creating a department: {} ", dto.getState());

        Department entity = mapper.map( dto, Department.class );
        entity = service.save(entity);
        return mapper.map(entity, DepartmentsDTO.class);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiExceptions(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleBusinessException(BusinessException ex){
        return new ApiExceptions(ex);
    }
}
