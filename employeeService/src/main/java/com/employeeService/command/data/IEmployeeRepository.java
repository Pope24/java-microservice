package com.employeeService.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> getEmployeesByIsDisciplined(Boolean isDisciplined);

}
