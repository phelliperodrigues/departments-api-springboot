package com.phellipe.departments.service;

import com.phellipe.departments.model.Department;
import com.phellipe.departments.model.enums.BoardDirector;
import com.phellipe.departments.repository.DepartmentsRepository;
import com.phellipe.departments.service.imp.DepartmentsServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class DepartmentsServiceTest {
    DepartmentsService service;

    @MockBean
    DepartmentsRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new DepartmentsServiceImp(repository);
    }

    @Test
    @DisplayName("Should return all departments")
    public void findAll(){
        List<Department> data = new ArrayList();
        Department department = createValidDepartments();
        Department department1 = createValidDepartments();
        Department department2 = createValidDepartments();
        data.add(department);
        data.add(department1);
        data.add(department2);
        given(repository.findAll()).willReturn(data);

        List<Department> expected = service.findAll();

        assertEquals(expected, data);

    }
    @Test
    @DisplayName("Should return a departments by id")
    public void findById() {
        Long id = 1L;
        Department department = createValidDepartments();
        department.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = service.getById(id);

        assertThat( foundDepartment.isPresent() ).isTrue();
        assertThat( foundDepartment.get().getId()).isEqualTo(id);
        assertThat( foundDepartment.get().getName()).isEqualTo(department.getName());
        assertThat( foundDepartment.get().getState()).isEqualTo(department.getState());
        assertThat( foundDepartment.get().getCity()).isEqualTo(department.getCity());
        assertThat( foundDepartment.get().getRegion()).isEqualTo(department.getRegion());
        assertThat( foundDepartment.get().getBoardDirector()).isEqualTo(department.getBoardDirector());
    }
    @Test
    @DisplayName("Should return empty departments by id")
    public void findByIdNotFound() {
        Long id = 1L;
        when( repository.findById(id) ).thenReturn(Optional.empty());

        Optional<Department> department = service.getById(id);

        assertThat( department.isPresent() ).isFalse();
    }

    @Test
    @DisplayName("Should save a departments")
    public void save() {
        Department department = createValidDepartments();

        when(repository.save(department)).thenReturn(
                Department.builder().id(1L)
                        .name("Dtp 1")
                        .region("Center")
                        .city("Sao Paulo")
                        .state("SP")
                        .boardDirector(BoardDirector.BUSINESS)
                        .build()
        );

        Department savedDepartment = service.save(department);

        assertThat(savedDepartment.getId()).isNotNull();
        assertThat(savedDepartment.getName()).isEqualTo("Dtp 1");
        assertThat(savedDepartment.getCity()).isEqualTo("Sao Paulo");
        assertThat(savedDepartment.getRegion()).isEqualTo("Center");
        assertThat(savedDepartment.getState()).isEqualTo("SP");
        assertThat(savedDepartment.getBoardDirector()).isNotNull();
    }

    @Test
    @DisplayName("Should delete a departments")
    public void delete() {
        Department department = Department.builder().id(1L).build();

        org.junit.jupiter.api.Assertions.assertDoesNotThrow( () -> service.delete(department) );

        verify(repository, times(1)).delete(department);
    }

    @Test
    @DisplayName("Should show error when delete a departments nonexistent")
    public void deleteNotFound() {
        Department department = new Department();

        assertThrows(IllegalArgumentException.class, () -> service.delete(department));

        verify( repository, never() ).delete(department);
    }

    @Test
    @DisplayName("Should show error when update a departments nonexistent")
    public void updateNotFound() {
        Department department = new Department();

        assertThrows(IllegalArgumentException.class, () -> service.update(department));

        verify( repository, never() ).save(department);
    }

    @Test
    @DisplayName("Should update a departments")
    public void update() {
        long id = 1L;

        Department updatingDepartment = Department.builder().id(id).build();

        Department updatedDepartment = createValidDepartments();
        updatedDepartment.setId(id);
        when(repository.save(updatingDepartment)).thenReturn(updatedDepartment);

        Department department = service.update(updatingDepartment);

        assertThat(department.getId()).isEqualTo(updatedDepartment.getId());
        assertThat(department.getId()).isNotNull();
        assertThat(department.getName()).isEqualTo(updatedDepartment.getName());
        assertThat(department.getCity()).isEqualTo(updatedDepartment.getCity());
        assertThat(department.getRegion()).isEqualTo(updatedDepartment.getRegion());
        assertThat(department.getState()).isEqualTo(updatedDepartment.getState());
        assertThat(department.getBoardDirector()).isNotNull();
    }

    @Test
    @DisplayName("Should find departments by parameters")
    public void find() {
        Department department = createValidDepartments();

        PageRequest pageRequest = PageRequest.of(0, 10);

        List<Department> list = Collections.singletonList(department);
        Page<Department> page = new PageImpl<Department>(list, pageRequest, 1);
        when( repository.findAll( any(Example.class), any(PageRequest.class)))
                .thenReturn(page);

        Page<Department> result = service.find(department, pageRequest);


        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(list);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
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
