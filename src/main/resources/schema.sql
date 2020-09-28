DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

create sequence employee_seq START WITH 1 INCREMENT BY 1;
create sequence dept_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE department (
  id INT default dept_seq.nextval PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
);

CREATE TABLE employee (
  id INT default employee_seq.nextval PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  dept_id int NOT NULL,
  sex VARCHAR(1) NOT NULL,
  tel VARCHAR(50) NOT NULL,
  address VARCHAR(200) NOT NULL,
  age INT NOT NULL,
  create_time DATETIME NOT NULL,
  last_modify_time DATETIME,
  FOREIGN KEY (dept_id) REFERENCES department(id)
);



