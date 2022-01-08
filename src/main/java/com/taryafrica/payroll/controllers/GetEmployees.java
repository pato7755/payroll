package com.taryafrica.payroll.controllers;

import com.taryafrica.payroll.models.Employee;
import com.taryafrica.payroll.models.ResponseClass;
import com.taryafrica.payroll.repositories.EmployeeRepository;
import com.taryafrica.payroll.utils.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class GetEmployees {

    private final EmployeeRepository employeeRepository;

    public GetEmployees(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/employees/{id}")
    private ResponseEntity<ResponseClass> getEmployeeById(@PathVariable("id") Long id) {

        Optional<Employee> employee;

        employee = employeeRepository.findById(id);

        if (employee.isPresent())
            return new ResponseEntity<>(
                    new ResponseClass(ResponseCodes.SUCCESS.getStatusCode(), "Successful", employee), HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    new ResponseClass(ResponseCodes.FAILURE.getStatusCode(), String.format("Employee with ID: %s does not exist", id), null), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/employees")
    private ResponseClass getEmployees() {

        List<Employee> employeeList;

        try {

            employeeList = employeeRepository.findAll();

            return new ResponseClass(ResponseCodes.SUCCESS.getStatusCode(), "Successful", employeeList);

        } catch (Exception ex) {

            ex.printStackTrace();

            return new ResponseClass(ResponseCodes.ERROR.getStatusCode(), "An error occurred while fetching employees");

        }

    }

}
