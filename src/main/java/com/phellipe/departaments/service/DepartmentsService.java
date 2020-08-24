package com.phellipe.departaments.service;

import com.phellipe.departaments.model.Department;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentsService {
    Department save(Department department);

    List<Department> findAll();
}
