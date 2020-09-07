package com.phellipe.departments.service.imp;

import com.phellipe.departments.model.Department;
import com.phellipe.departments.repository.DepartmentsRepository;
import com.phellipe.departments.service.DepartmentsService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        return repository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Department> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Department> find(Department filter, Pageable pageRequest) {
        Example<Department> example = Example.of(filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING )
        ) ;
        return repository.findAll(example, pageRequest);
    }

    @Override
    public Department update(Department department) {
        if(department == null || department.getId() == null){
            throw new IllegalArgumentException("Department id cant be null.");
        }
        return this.repository.save(department);
    }

    @Override
    public void delete(Department department) {
        if(department == null || department.getId() == null){
            throw new IllegalArgumentException("Department id cant be null.");
        }
        this.repository.delete(department);
    }
}
