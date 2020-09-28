# employee-api
default host
http://localhost:8080

API docs
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs/

please refer to postman test script
/integration-test/postman/employee-api.postman_collection.json

API list
GET /api/employees
POST /api/employees
PUT /api/employees/{id}
DELETE /api/employees/{id}
POST /api/departments
PUT /api/departments/{id}
DELETE /api/departments/{id}

Unit Test 
EmployeeServiceImplTest

other method has no logic involved so only EmployeeServiceImpl.getEmployees need unit test 

