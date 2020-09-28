package com.demo.repository;

import com.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor {

    @Override
    @EntityGraph(attributePaths = {"department"})
    Page<Employee> findAll(@Nullable Specification var1, Pageable var2);
}
