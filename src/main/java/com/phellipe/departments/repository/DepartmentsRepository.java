package com.phellipe.departments.repository;

import com.phellipe.departments.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentsRepository extends JpaRepository<Department, Long> {
}
