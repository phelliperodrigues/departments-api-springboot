package com.phellipe.departaments.service.imp;

import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.repository.DepartmentsRepository;
import com.phellipe.departaments.service.DepartmentsService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentsServiceImp implements DepartmentsService {

    private DepartmentsRepository repository;

    public DepartmentsServiceImp(DepartmentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department save(Department department) {
        return null;
    }
}
