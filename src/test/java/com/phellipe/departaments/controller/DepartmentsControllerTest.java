package com.phellipe.departaments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phellipe.departaments.service.DepartmentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = DepartmentsController.class)
@AutoConfigureMockMvc
public class DepartmentsControllerTest {

    private static final String DEPARTMENTS_API = "api/departments";

    @Autowired
    MockMvc mvc;

    @MockBean
    DepartmentsService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("[CREATE] - Should create a valid departments")
    public void createValid() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show exception when create a invalid departments")
    public void showExceptionWhenCreateInvalid() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that name required")
    public void createInvalidDepartmentsWithoutNameTest() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that region required")
    public void createInvalidDepartmentsWithoutRegionTest() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that city required")
    public void createInvalidDepartmentsWithoutCityTest() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that state required")
    public void createInvalidDepartmentsWithoutStateTest() throws Exception {

    }

    @Test
    @DisplayName("[CREATE] - Should show message exceptions that board directors required")
    public void createInvalidDepartmentsWithoutBoardDirectorsTest() throws Exception {

    }

    @Test
    @DisplayName("[READ] - Should return all Departments")
    public void showAll() throws Exception {

    }

    @Test
    @DisplayName("[READ] - Should return Departments by ID")
    public void findById() throws Exception {

    }

    @Test
    @DisplayName("[READ] - Should return 404 Not Found find by ID")
    public void findByIdNotFound() throws Exception {

    }

    @Test
    @DisplayName("[READ] - Should filter a departments with parameters")
    public void findDepartments() throws Exception {

    }

    @Test
    @DisplayName("[UPDATE] - Should update Departments by ID")
    public void update() throws Exception {

    }

    @Test
    @DisplayName("[UPDATE] - Should return 404 Not Found update by ID")
    public void updateNotFound() throws Exception {

    }


    @Test
    @DisplayName("[DELETE] - Should delete Departments by ID")
    public void delete() throws Exception {

    }

    @Test
    @DisplayName("[DELETE] - Should return exception delete by ID nonexistent")
    public void deleteNotFound() throws Exception {

    }
}
