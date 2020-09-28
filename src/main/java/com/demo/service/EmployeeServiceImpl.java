package com.demo.service;

import com.demo.entity.Employee;
import com.demo.repository.EmployeeRepository;
import com.demo.util.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees(String name, Integer id, Integer age, String deptName, int page, int size) {
        Specification specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();

            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            if (age != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), age));
            }

            Join departmentJoin;
            Path departmentNamePath;
            if (deptName != null) {
                departmentJoin = root.join("department");
                departmentNamePath = departmentJoin.get("name");
                predicates.add(criteriaBuilder.equal(departmentNamePath, deptName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Pageable pageable = PageRequest.of(page, size > 10 ? 10 : size);
        return employeeRepository.findAll(specification, pageable).stream().collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Employee update(Integer id, Employee employee) throws Exception {
        Employee currentData = employeeRepository.findById(id).orElseThrow(() -> new Exception(" data not found "));
        BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
        notNull.copyProperties(currentData, employee);
        return employeeRepository.save(currentData);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
