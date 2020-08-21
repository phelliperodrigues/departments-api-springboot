package com.phellipe.departaments.service;

import com.phellipe.departaments.repository.DepartmentsRepository;
import com.phellipe.departaments.service.imp.DepartmentsServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class DepartmentsServiceTest {
    DepartmentsService service;

    @MockBean
    DepartmentsRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new DepartmentsServiceImp(repository);
    }

    @Test
    @DisplayName("Should return a departments by id")
    public void findById() {

    }
    @Test
    @DisplayName("Should return empty departments by id")
    public void findByIdNotFound() {

    }

    @Test
    @DisplayName("Should save a departments")
    public void save() {

    }

    @Test
    @DisplayName("Should delete a departments")
    public void delete() {

    }

    @Test
    @DisplayName("Should show error when delete a departments nonexistent")
    public void deleteNotFound() {

    }

    @Test
    @DisplayName("Should show error when update a departments nonexistent")
    public void updateNotFound() {

    }

    @Test
    @DisplayName("Should update a departments")
    public void update() {

    }

    @Test
    @DisplayName("Should find departments by parameters")
    public void find() {

    }
}
