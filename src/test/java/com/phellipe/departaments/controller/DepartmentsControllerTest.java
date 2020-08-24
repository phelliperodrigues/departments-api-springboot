package com.phellipe.departaments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phellipe.departaments.dto.DepartmentsDTO;
import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.model.enums.BoardDirector;
import com.phellipe.departaments.service.DepartmentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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
        BDDMockito.given(service.save(Mockito.any(Department.class))).willReturn(saved);

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
        assertThat(1, equalTo(0));
    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that region required")
    public void createInvalidDepartmentsWithoutRegionTest() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that city required")
    public void createInvalidDepartmentsWithoutCityTest() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that state required")
    public void createInvalidDepartmentsWithoutStateTest() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that board directors required")
    public void createInvalidDepartmentsWithoutBoardDirectorsTest() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[READ] - Should return all Departments")
    public void showAll() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[READ] - Should return Departments by ID")
    public void findById() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[READ] - Should return 404 Not Found find by ID")
    public void findByIdNotFound() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[READ] - Should filter a departments with parameters")
    public void findDepartments() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[UPDATE] - Should update Departments by ID")
    public void update() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[UPDATE] - Should return 404 Not Found update by ID")
    public void updateNotFound() throws Exception {
        assertThat(1, equalTo(0));

    }


    @Test
    @DisplayName("[DELETE] - Should delete Departments by ID")
    public void delete() throws Exception {
        assertThat(1, equalTo(0));

    }

    @Test
    @DisplayName("[DELETE] - Should return exception delete by ID nonexistent")
    public void deleteNotFound() throws Exception {
        assertThat(1, equalTo(0));

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
