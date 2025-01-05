package com.employeeService.command.controller;

import com.employeeService.command.command.CreateEmployeeCommand;
import com.employeeService.command.command.DeleteEmployeeCommand;
import com.employeeService.command.command.UpdateEmployeeCommand;
import com.employeeService.command.model.EmployeeRequestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;
    @PostMapping
    public String createEmployee(@RequestBody EmployeeRequestModel request) {
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(), request.getFirstName(), request.getLastName(), request.getKIN(), request.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }
    @PutMapping("/{employeeId}")
    public String updateEmployee(@Valid @RequestBody EmployeeRequestModel request, @PathVariable String employeeId) {
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId, request.getFirstName(), request.getLastName(), request.getKIN(), request.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
