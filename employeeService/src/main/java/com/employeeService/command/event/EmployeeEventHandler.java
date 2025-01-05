package com.employeeService.command.event;

import com.employeeService.command.data.Employee;
import com.employeeService.command.data.IEmployeeRepository;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeEventHandler {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @EventHandler
    @Transactional
    public void saveBook(EmployeeCreatedEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    @Transactional
    @DisallowReplay
    public void updateBook(EmployeeUpdatedEvent event) throws Exception {
        Employee employee = employeeRepository.findById(event.getId()).orElseThrow(() -> new Exception("Can not find employee with ID: " + event.getId()));
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKIN(event.getKIN());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }
    @EventHandler
    @Transactional
    @DisallowReplay
    public void deleteBook(EmployeeDeletedEvent event) throws Exception {
        Employee employee = employeeRepository.findById(event.getId()).orElseThrow(() -> new Exception("Can not find employee with ID: " + event.getId()));
        employeeRepository.delete(employee);
    }
}
