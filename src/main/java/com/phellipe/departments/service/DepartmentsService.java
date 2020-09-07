package com.phellipe.departments.service;

import com.phellipe.departments.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentsService {
    Department save(Department department);

    List<Department> findAll();

    Optional<Department> getById(Long id);

    Page<Department> find(Department filter, Pageable pageRequest);

    Department update(Department department);

    void delete(Department department);
}
