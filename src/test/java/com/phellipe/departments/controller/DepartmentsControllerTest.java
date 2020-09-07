package com.phellipe.departments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phellipe.departments.dto.DepartmentsDTO;
import com.phellipe.departments.model.Department;
import com.phellipe.departments.model.enums.BoardDirector;
import com.phellipe.departments.service.DepartmentsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = DepartmentsController.class)
@AutoConfigureMockMvc
public class DepartmentsControllerTest {

    private static final String DEPARTMENTS_API = "/api/departments";

    @Autowired
    MockMvc mvc;

    @MockBean
    DepartmentsService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("[CREATE] - Should success create a departments")
    public void createValid() throws Exception {

        DepartmentsDTO dto = createValidDepartments();
        Department saved = Department.builder()
                .id(10L)
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();
        given(service.save(Mockito.any(Department.class))).willReturn(saved);

        String json = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder request =
                post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);
        mvc
                .perform(request)
                .andExpect( status().isCreated() )
                .andExpect( jsonPath("id").value(10L) )
                .andExpect( jsonPath("name").value(dto.getName()) )
                .andExpect( jsonPath("city").value(dto.getCity()) )
                .andExpect( jsonPath("state").value(dto.getState()) )
                .andExpect( jsonPath("region").value(dto.getRegion()) )
                .andExpect( jsonPath("boardDirector").isNotEmpty() )

        ;
    }


    @Test
    @DisplayName("[CREATE] - Should show exception when create a invalid departments")
    public void showExceptionWhenCreateInvalid() throws Exception {
        String json = mapper.writeValueAsString(new DepartmentsDTO());

        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(5)));
    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that name required")
    public void createInvalidDepartmentsWithoutNameTest() throws Exception {

        DepartmentsDTO dto =  DepartmentsDTO.builder()
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        Department savedDepartment = Department.builder()
                .id(1L)
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given(service.save(Mockito.any(Department.class))).willReturn(savedDepartment);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("Name is required"));
    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that region required")
    public void createInvalidDepartmentsWithoutRegionTest() throws Exception {
        DepartmentsDTO dto =  DepartmentsDTO.builder()
                .name("Dpt 1")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        Department savedDepartment = Department.builder()
                .id(1L)
                .name("Dpt 1")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given(service.save(Mockito.any(Department.class))).willReturn(savedDepartment);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("Region is required"));

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that city required")
    public void createInvalidDepartmentsWithoutCityTest() throws Exception {
        DepartmentsDTO dto =  DepartmentsDTO.builder()
                .name("Dpt 1")
                .region("Center")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        Department savedDepartment = Department.builder()
                .id(1L)
                .name("Dpt 1")
                .region("Center")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given(service.save(Mockito.any(Department.class))).willReturn(savedDepartment);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("City is required"));


    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that state required")
    public void createInvalidDepartmentsWithoutStateTest() throws Exception {
        DepartmentsDTO dto =  DepartmentsDTO.builder()
                .name("Dpt 1")
                .region("Center")
                .city("Sao Paulo")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        Department savedDepartment = Department.builder()
                .id(1L)
                .name("Dpt 1")
                .region("Center")
                .city("Sao Paulo")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given(service.save(Mockito.any(Department.class))).willReturn(savedDepartment);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("State is required"));


    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that board directors required")
    public void createInvalidDepartmentsWithoutBoardDirectorsTest() throws Exception {
        DepartmentsDTO dto =  DepartmentsDTO.builder()
                .name("Dpt 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .build();

        Department savedDepartment = Department.builder()
                .id(1L)
                .name("Dpt 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .build();

        given(service.save(Mockito.any(Department.class))).willReturn(savedDepartment);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder request = post(DEPARTMENTS_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value("Board directors is required"));


    }

    @Test
    @DisplayName("[READ] - Should return all Departments")
    public void showAll() throws Exception {
        Long id = 1l;

        Department department = Department.builder()
                .id(id)
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given( service.findAll() )
                .willReturn(Collections.singletonList(department));


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DEPARTMENTS_API.concat("/all"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform( request )
                .andExpect( status().isOk() )
                .andExpect( jsonPath("$", Matchers.hasSize(1)))
        ;

    }

    @Test
    @DisplayName("[READ] - Should return Departments by ID")
    public void findById() throws Exception {
        Long id = 1L;

        Department department = Department.builder()
                .id(id)
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given( service.getById(id) ).willReturn(Optional.of(department));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DEPARTMENTS_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect( jsonPath("id").value(id) )
                .andExpect( jsonPath("name").value(createValidDepartments().getName()) )
                .andExpect( jsonPath("region").value(createValidDepartments().getRegion()) )
                .andExpect( jsonPath("city").value(createValidDepartments().getCity()) )
                .andExpect( jsonPath("state").value(createValidDepartments().getState()) )
                .andExpect( jsonPath("boardDirector").isNotEmpty() )
        ;

    }

    @Test
    @DisplayName("[READ] - Should return 404 Not Found find by ID")
    public void findByIdNotFound() throws Exception {

        given( service.getById(anyLong()) ).willReturn( empty() );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DEPARTMENTS_API.concat("/" + 1))
                .accept(MediaType.APPLICATION_JSON);

        mvc
            .perform(request)
            .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("[READ] - Should filter a departments with parameters")
    public void findDepartments() throws Exception {
        Long id = 1L;

        Department department = Department.builder()
                .id(id)
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given(
                service.find(Mockito.any(Department.class),
                Mockito.any(Pageable.class))
        ).willReturn(
                new PageImpl<Department>(
                        Collections.singletonList(department),
                        PageRequest.of(0,100),
                        1
                )
        );

        String queryString = String.format("?name=%s&state=%s&page=0&size=100",
                department.getName(), department.getState());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DEPARTMENTS_API.concat(queryString))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform( request )
                .andExpect( status().isOk() )
                .andExpect( jsonPath("content", Matchers.hasSize(1)))
                .andExpect( jsonPath("totalElements").value(1) )
                .andExpect( jsonPath("pageable.pageSize").value(100) )
                .andExpect( jsonPath("pageable.pageNumber").value(0))
        ;

    }

    @Test
    @DisplayName("[UPDATE] - Should update Departments by ID")
    public void update() throws Exception {
        Long id = 1l;
        String json = new ObjectMapper().writeValueAsString(createValidDepartments());

        Department updatingDepartment = Department.builder()
                .id(id)
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();

        given( service.getById(id) ).willReturn( Optional.of(updatingDepartment) );
        Department updatedDepartment =
                Department.builder()
                        .id(id)
                        .name("Dtp 2")
                        .region("South Zone")
                        .city("Sao Paulo")
                        .state("SP")
                        .boardDirector(BoardDirector.BUSINESS)
                        .build();
        given(service.update(updatingDepartment)).willReturn(updatedDepartment);

        MockHttpServletRequestBuilder request = put(DEPARTMENTS_API.concat("/" + id))
                .content(json)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);

        mvc.perform( request )
                .andExpect(status().isOk())
                .andExpect( jsonPath("id").value(id) )
                .andExpect( jsonPath("name").value("Dtp 2") )
                .andExpect( jsonPath("region").value("South Zone") )
                .andExpect( jsonPath("city").value(createValidDepartments().getCity()) )
                .andExpect( jsonPath("state").value(createValidDepartments().getState()) )
                .andExpect( jsonPath("boardDirector").isNotEmpty() );
    }

    @Test
    @DisplayName("[UPDATE] - Should return 404 Not Found update by ID")
    public void updateNotFound() throws Exception {

        String json = new ObjectMapper().writeValueAsString(createValidDepartments());
        given( service.getById(anyLong()) )
                .willReturn( empty() );

        MockHttpServletRequestBuilder request = put(DEPARTMENTS_API.concat("/" + 1))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform( request )
                .andExpect( status().isNotFound() );

    }


    @Test
    @DisplayName("[DELETE] - Should delete Departments by ID")
    public void delete() throws Exception {
        given(service.getById(anyLong()))
                .willReturn(Optional.of(Department.builder().id(1L).build()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(DEPARTMENTS_API.concat("/" + 1));

        mvc.perform( request )
                .andExpect( status().isNoContent() );

    }

    @Test
    @DisplayName("[DELETE] - Should return exception delete by ID nonexistent")
    public void deleteNotFound() throws Exception {
        given(service.getById(anyLong())).willReturn(empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(DEPARTMENTS_API.concat("/" + 1));

        mvc.perform( request )
                .andExpect( status().isNotFound() );

    }

    private DepartmentsDTO createValidDepartments() {
        return DepartmentsDTO.builder()
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();
    }
}
