package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.events.EmployeeFiredEvent;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
    // Loose Coupling -> EmployeeRepository is an interface
	// Dependency Injection -> Constructor Injection
	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getKimlikNo();
		if (employeeRepository.existsByIdentity(identity)) {
			throw new IllegalArgumentException("Employee already exists.");
		}
        Employee emp = employeeRepository.persist(employee);
        eventPublisher.publish(new EmployeeHiredEvent(identity));
		return emp;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo kimlikNo) {
		Optional<Employee> employee = employeeRepository.removeByIdentity(kimlikNo);
		if (employee.isPresent()) {
			eventPublisher.publish(new EmployeeFiredEvent(kimlikNo));			
		}
		return employee;
	}

}
