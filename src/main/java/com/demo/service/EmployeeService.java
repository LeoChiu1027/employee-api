package com.demo.service;

import com.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(String name, Integer id, Integer age, String deptName, int page, int size);

    Employee create(Employee employee);

    Employee update(Integer id, Employee employee) throws Exception;

    void delete(Integer id);
}
