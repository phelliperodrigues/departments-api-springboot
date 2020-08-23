package com.phellipe.departaments.service.imp;

import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.repository.DepartmentsRepository;
import com.phellipe.departaments.service.DepartmentsService;

public class DepartmentsServiceImp implements DepartmentsService {
    public DepartmentsServiceImp(DepartmentsRepository repository) {
        super();
    }

    @Override
    public Department save(Department department) {
        return null;
    }
}
