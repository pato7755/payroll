package com.taryafrica.payroll.controllers;

import com.taryafrica.payroll.models.Employee;
import com.taryafrica.payroll.models.ResponseClass;
import com.taryafrica.payroll.repositories.EmployeeRepository;
import com.taryafrica.payroll.services.SequenceGeneratorService;
import com.taryafrica.payroll.utils.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
public class AddEmployee {

    private final EmployeeRepository employeeRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    public AddEmployee(EmployeeRepository employeeRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.employeeRepository = employeeRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @PostMapping(path = "/employees")
    public ResponseClass addEmployee(@Valid @RequestBody Employee employee) {

        ResponseClass response;
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));

        try {

            employeeRepository.save(employee);

            response = new ResponseClass(ResponseCodes.SUCCESS.getStatusCode(), "Employee added successfully");

        } catch (Exception exception) {

            response = new ResponseClass(ResponseCodes.FAILURE.getStatusCode(), "An error occurred while adding employee");

        }

        return response;
    }

}
