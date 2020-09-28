package com.demo.service;

import com.demo.entity.Department;
import com.demo.repository.DepartmentRepository;
import com.demo.util.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Integer id, Department department) throws Exception {
        Department currentData = departmentRepository.findById(id).orElseThrow(() -> new Exception(" data not found "));
        BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
        notNull.copyProperties(currentData, department);
        return departmentRepository.save(currentData);
    }

    @Override
    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }
}
