package com.demo.service;

import com.demo.entity.Department;

public interface DepartmentService {

    Department create(Department department);

    Department update(Integer id, Department department) throws Exception;

    void delete(Integer id);

}
