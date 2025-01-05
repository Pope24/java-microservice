package com.employeeService.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String KIN;
    private Boolean isDisciplined;
}
