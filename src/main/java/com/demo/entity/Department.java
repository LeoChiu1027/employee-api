package com.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "deptId_generator")
    @SequenceGenerator(name="deptId_generator", sequenceName = "dept_seq", allocationSize = 1)
    private Integer id;

    private String name;

}
