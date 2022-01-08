package com.taryafrica.payroll.controllers;

import com.taryafrica.payroll.models.Employee;
import com.taryafrica.payroll.models.ResponseClass;
import com.taryafrica.payroll.repositories.EmployeeRepository;
import com.taryafrica.payroll.utils.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UpdateEmployee {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PutMapping(path = "employees")
    private ResponseEntity<ResponseClass> updateEmployee(@RequestBody Employee employee) {

        Optional<Employee> employeeResult = employeeRepository.findById(employee.getId());

        try {

            if (employeeResult.isPresent()) {
                Employee _employee = employeeResult.get();
                _employee.setFirstName(employee.getFirstName());
                _employee.setLastName(employee.getLastName());
                _employee.setRole(employee.getRole());
                _employee.setEmail(employee.getEmail());

                employeeRepository.save(_employee);

                return new ResponseEntity<>(
                        new ResponseClass(ResponseCodes.SUCCESS.getStatusCode(), "Employee details updated successfully", employee), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(
                        new ResponseClass(ResponseCodes.FAILURE.getStatusCode(), String.format("Employee with ID: %s does not exist", employee.getId()), null), HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseClass(ResponseCodes.ERROR.getStatusCode(), "An error occurred while updating employee", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
