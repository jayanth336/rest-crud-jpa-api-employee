package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private final EntityManager entityManager;

    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> getListOfEmployees = entityManager.createQuery("from Employee", Employee.class);
        //return the results
        return getListOfEmployees.getResultList();
    }

    @Override
    public Employee findById(int id) {
        //get the employee
        Employee employee = entityManager.find(Employee.class, id);
        //return the employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        //save or update the employee
        Employee dbEmployee = entityManager.merge(employee);
        //return the latest employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //get the employee having the specified id
        Employee theEmployee = entityManager.find(Employee.class, id);
        //remove theEmployee from DB
        entityManager.remove(theEmployee);
    }
}
