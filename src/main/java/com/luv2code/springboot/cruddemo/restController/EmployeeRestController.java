package com.luv2code.springboot.cruddemo.restController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //to get list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //to get a single employee based on id
    @GetMapping("/employees/{id}")
    public Employee findEmployeeById(@PathVariable int id) {

        Employee theEmployee = employeeService.findById(id);
        if(theEmployee == null) {
            throw new RuntimeException("Employee ID not found : " + id);
        }
        return theEmployee;
    }

    //to add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        /*
        also just in case they pass an id a user in JSON.. set it to 0
        this is to force a save on new item.. instead of update
        because we may already have data with id given by the user.. but not 0 id in database
         */
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee dbEmployee = employeeService.findById(id);
        if(dbEmployee == null) {
            throw new RuntimeException("Employee ID not found : " + id);
        }
        employeeService.deleteById(id);

        return "Deleted employee id : " + id;
    }
}
