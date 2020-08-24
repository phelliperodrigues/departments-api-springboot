package com.phellipe.departaments.service.imp;

import com.phellipe.departaments.model.Department;
import com.phellipe.departaments.repository.DepartmentsRepository;
import com.phellipe.departaments.service.DepartmentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Optional<Department> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Department> find(Department filter, Pageable pageRequest) {
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void delete(Department department) {

    }
}
