package com.employeeService.command.aggregate;

import com.employeeService.command.command.CreateEmployeeCommand;
import com.employeeService.command.command.DeleteEmployeeCommand;
import com.employeeService.command.command.UpdateEmployeeCommand;
import com.employeeService.command.event.EmployeeCreatedEvent;
import com.employeeService.command.event.EmployeeDeletedEvent;
import com.employeeService.command.event.EmployeeUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String KIN;
    private Boolean isDisciplined;
    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void on(UpdateEmployeeCommand command) {
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void on(DeleteEmployeeCommand command) {
        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.KIN = event.getKIN();
        this.isDisciplined = event.getIsDisciplined();
    }
    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.KIN = event.getKIN();
        this.isDisciplined = event.getIsDisciplined();
    }
    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        this.id = event.getId();
    }
}
