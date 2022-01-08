package com.taryafrica.payroll.controllers;

import com.taryafrica.payroll.models.Employee;
import com.taryafrica.payroll.models.ResponseClass;
import com.taryafrica.payroll.repositories.EmployeeRepository;
import com.taryafrica.payroll.utils.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class DeleteEmployee {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @DeleteMapping(path = "employees/{id}")
    private ResponseEntity<ResponseClass> deleteEmployee(@PathVariable("id") Long id) {

        Optional<Employee> employee;

        employee = employeeRepository.findById(id);

        try {

            if (employee.isPresent()) {
                employeeRepository.deleteById(id);
                return new ResponseEntity<>(
                        new ResponseClass(ResponseCodes.SUCCESS.getStatusCode(), "Employee deleted successfully", employee), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseClass(ResponseCodes.FAILURE.getStatusCode(), String.format("Employee with ID: %s does not exist", id), null), HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseClass(ResponseCodes.ERROR.getStatusCode(), "An error occurred while deleting employee", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
