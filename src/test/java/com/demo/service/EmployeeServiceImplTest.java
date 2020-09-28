package com.demo.service;

import com.demo.entity.Employee;
import com.demo.repository.EmployeeRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest extends TestCase {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock(extraInterfaces = Serializable.class)
    private Root<Employee> root;
    @Mock(extraInterfaces = Serializable.class)
    private CriteriaQuery<?> query;
    @Mock(extraInterfaces = Serializable.class)
    private CriteriaBuilder builder;


    @Test
    public void testGetEmployeesCase1AllArgsAndSizeGreaterThan10(){

        EmployeeServiceImpl spy = spy(employeeService);
        when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl(new ArrayList()));
        Join join = mock(Join.class);
        Path path = mock(Path.class);
        when(root.join("department")).thenReturn(join);
        when(join.get("name")).thenReturn(path);

        ArgumentCaptor<Specification> specArg = ArgumentCaptor.forClass(Specification.class);
        ArgumentCaptor<Pageable> pageArg = ArgumentCaptor.forClass(Pageable.class);

        spy.getEmployees("測試員工", 999, 30, "資訊部", 0, 20);

        verify(employeeRepository).findAll(specArg.capture(), pageArg.capture());
        assertEquals(10, pageArg.getValue().getPageSize());

        Predicate predicate = specArg.getValue().toPredicate(root,query,builder);
        verify(builder, times(1)).equal(root.get("name"), "測試員工");
        verify(builder, times(1)).equal(root.get("id"), 999);
        verify(builder, times(1)).equal(root.get("age"), 30);
        verify(builder, times(1)).equal(path, "資訊部");

    }

    @Test
    public void testGetEmployeesCase2SomeArgsAndSizeLessThan10(){
        EmployeeServiceImpl spy = spy(employeeService);
        when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl(new ArrayList()));
        Join join = mock(Join.class);
        Path path = mock(Path.class);
        when(root.join("department")).thenReturn(join);
        when(join.get("name")).thenReturn(path);

        ArgumentCaptor<Specification> specArg = ArgumentCaptor.forClass(Specification.class);
        ArgumentCaptor<Pageable> pageArg = ArgumentCaptor.forClass(Pageable.class);

        spy.getEmployees(null, null, 30, "資訊部", 0, 5);

        verify(employeeRepository).findAll(specArg.capture(), pageArg.capture());
        assertEquals(5, pageArg.getValue().getPageSize());

        Predicate predicate = specArg.getValue().toPredicate(root,query,builder);
        verify(builder, times(0)).equal(root.get("name"), null);
        verify(builder, times(0)).equal(root.get("id"), null);
        verify(builder, times(1)).equal(root.get("age"), 30);
        verify(builder, times(1)).equal(path, "資訊部");
    }
}