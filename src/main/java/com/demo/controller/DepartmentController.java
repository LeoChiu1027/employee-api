package com.demo.controller;

import com.demo.entity.Department;
import com.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/departments"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @PostMapping({""})
    public ResponseEntity<Department> create (@RequestBody Department department) {
        try {
            return ResponseEntity.ok(this.departmentService.create(department));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseBody
    @PutMapping({"/{id}"})
    public ResponseEntity<Department> update (@PathVariable Integer id, @RequestBody Department department) {
        try {
            return ResponseEntity.ok(this.departmentService.update(id, department));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseBody
    @DeleteMapping({"/{id}"})
    public void delete (@PathVariable Integer id) {
        this.departmentService.delete(id);
    }
}
