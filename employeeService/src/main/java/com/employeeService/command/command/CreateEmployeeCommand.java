package com.employeeService.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String KIN;
    private Boolean isDisciplined;
}
