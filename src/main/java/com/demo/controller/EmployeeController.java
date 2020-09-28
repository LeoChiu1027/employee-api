package com.demo.controller;

import com.demo.entity.Employee;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/employees"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @GetMapping({""})
    public ResponseEntity<List<Employee>> getAllCases(@RequestParam(value = "name",required = false) String name,
                                                      @RequestParam(value = "id",required = false) Integer id,
                                                      @RequestParam(value = "age",required = false) Integer age,
                                                      @RequestParam(value = "deptName",required = false) String deptName,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(this.employeeService.getEmployees(name, id, age, deptName, page, size));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseBody
    @PostMapping({""})
    public ResponseEntity<Employee> create (@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(this.employeeService.create(employee));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseBody
    @PutMapping({"/{id}"})
    public ResponseEntity<Employee> update (@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(this.employeeService.update(id, employee));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseBody
    @DeleteMapping({"/{id}"})
    public void delete (@PathVariable Integer id) {
        this.employeeService.delete(id);
    }

}
