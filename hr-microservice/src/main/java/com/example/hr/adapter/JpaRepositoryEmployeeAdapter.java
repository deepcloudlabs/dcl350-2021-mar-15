package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "jpa")
public class JpaRepositoryEmployeeAdapter implements EmployeeRepository {
	@Autowired 
	private EmployeeEntityRepository employeeEntityRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		// Employee --> EmployeeEntity
		var employeeEntity = mapper.map(employee, EmployeeEntity.class);
		var emp = employeeEntityRepository.save(employeeEntity);
		// EmployeeEntity --> Employee
		return mapper.map(emp, Employee.class);
	}

	@Override
	public Optional<Employee> removeByIdentity(TcKimlikNo kimlikNo) {
		var employeeEntity = employeeEntityRepository.findById(kimlikNo.getValue());
		if (employeeEntity.isEmpty())
			return Optional.empty();
		EmployeeEntity emp = employeeEntity.get();
		employeeEntityRepository.delete(emp);
		// EmployeeEntity --> Employee
		var employee = mapper.map(emp, Employee.class);
		return Optional.of(employee);
	}

}
