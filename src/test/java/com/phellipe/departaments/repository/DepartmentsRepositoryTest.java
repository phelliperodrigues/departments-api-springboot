package com.phellipe.departaments.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    }

    @Test
    @DisplayName("Should save a departments")
    public void save() {

    }

    @Test
    @DisplayName("Should delete a departments")
    public void delete() {

    }


}
