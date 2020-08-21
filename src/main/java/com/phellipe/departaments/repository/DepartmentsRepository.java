package com.phellipe.departaments.repository;

import com.phellipe.departaments.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Department, Long> {
}
