package com.employeeService.query.projection;

import com.employeeService.command.data.Employee;
import com.employeeService.command.data.IEmployeeRepository;
import com.employeeService.query.model.EmployeeResponseModel;
import com.employeeService.query.queries.GetAllEmployeeQuery;
import com.employeeService.query.queries.GetDetailEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeProjection {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @QueryHandler
    public List<EmployeeResponseModel> getAllEmployee(GetAllEmployeeQuery query) {
        return employeeRepository.getEmployeesByIsDisciplined(query.getIsDisciplined()).stream().map(employee -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, model);
            return model;
        }).collect(Collectors.toList());
    }
    @QueryHandler
    public EmployeeResponseModel getDetailEmployee(GetDetailEmployeeQuery query) throws Exception {
        Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new Exception("Can not found employee by ID: " + query.getId()));
        EmployeeResponseModel response = new EmployeeResponseModel();
        BeanUtils.copyProperties(employee, response);
        return response;
    }
}
