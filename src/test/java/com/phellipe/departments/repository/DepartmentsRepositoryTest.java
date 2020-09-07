package com.phellipe.departments.repository;

import com.phellipe.departments.model.Department;
import com.phellipe.departments.model.enums.BoardDirector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class DepartmentsRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    DepartmentsRepository repository;

    @Test
    @DisplayName("Should return a departments by id")
    public void findById() {
        Department department = createValidDepartments();
        entityManager.persist(department);

        Optional<Department> foundBook = repository.findById(department.getId());

        assertThat(foundBook.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should save a departments")
    public void save() {

        Department department = createValidDepartments();

        Department saved = repository.save(department);

        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("Should delete a departments")
    public void delete() {
        Department department = createValidDepartments();
        entityManager.persist(department);
        Department foundDepartment = entityManager.find( Department.class, department.getId() );

        repository.delete(foundDepartment);

        Department deletedDepartment = entityManager.find(Department.class, department.getId());
        assertThat(deletedDepartment).isNull();

    }

    private Department createValidDepartments() {
        return Department.builder()
                .name("Dtp 1")
                .region("Center")
                .city("Sao Paulo")
                .state("SP")
                .boardDirector(BoardDirector.BUSINESS)
                .build();
    }
}
