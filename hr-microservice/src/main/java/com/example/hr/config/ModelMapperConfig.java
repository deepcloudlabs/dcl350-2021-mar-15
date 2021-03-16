package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.EmployeeKafkaEvent;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.events.EmployeeEvent;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(employeeDocument2Employee, EmployeeDocument.class, Employee.class);
		mapper.addConverter(employee2EmployeeDocument, Employee.class, EmployeeDocument.class);
		mapper.addConverter(employeeEntity2Employee, EmployeeEntity.class, Employee.class);
		mapper.addConverter(employee2EmployeeEntity, Employee.class, EmployeeEntity.class);
		mapper.addConverter(employeeEvent2EmployeeKafkaEvent, EmployeeEvent.class, EmployeeKafkaEvent.class);
		return mapper;
	}

	// EmployeeEvent --> EmployeeKafkaEvent
	private static final Converter<EmployeeEvent,EmployeeKafkaEvent> employeeEvent2EmployeeKafkaEvent
	   = context -> {
		   return new EmployeeKafkaEvent(context.getSource().getKimlikNo().getValue());
	   };
	   
	// EmployeeDocument --> Employee
	private static final Converter<EmployeeDocument,Employee> employeeDocument2Employee
	   = contex -> {
		   var empDoc = contex.getSource();
		   return new Employee.Builder(TcKimlikNo.valueOf(empDoc.getKimlikNo()))
				                .fullname(empDoc.getFullname())
				                .birthYear(empDoc.getBirthYear())
				                .iban(empDoc.getIban())
				                .salary(empDoc.getSalary(),FiatCurrency.TRY)
				                .jobType(empDoc.getJobType().name())
				                .department(empDoc.getDepartment().name())
				                .photo(empDoc.getPhoto().getBytes())
				                .build();
	   };
	// Employee --> EmployeeDocument
	private static final Converter<Employee,EmployeeDocument> employee2EmployeeDocument
	   = contex -> {
		   var employee = contex.getSource();
		   var fullname = employee.getFullname();
		   var empDoc = new EmployeeDocument();
		   empDoc.setKimlikNo(employee.getKimlikNo().getValue());
		   empDoc.setFullname(fullname.getFirstName()+" "+fullname.getLastName());
		   empDoc.setSalary(employee.getSalary().getValue());
		   empDoc.setBirthYear(employee.getBirthYear().getValue());
		   empDoc.setIban(employee.getIban().getValue());
		   empDoc.setDepartment(employee.getDepartment());
		   empDoc.setJobType(employee.getJobType());
		   empDoc.setPhoto(new String(employee.getPhoto().getData()));
		   return empDoc;
	   };
	   // EmployeeEntity --> Employee
	   private static final Converter<EmployeeEntity,Employee> employeeEntity2Employee
	   = contex -> {
		   var empDoc = contex.getSource();
		   return new Employee.Builder(TcKimlikNo.valueOf(empDoc.getKimlikNo()))
				   .fullname(empDoc.getFullname())
				   .birthYear(empDoc.getBirthYear())
				   .iban(empDoc.getIban())
				   .salary(empDoc.getSalary(),FiatCurrency.TRY)
				   .jobType(empDoc.getJobType().name())
				   .department(empDoc.getDepartment().name())
				   .photo(empDoc.getPhoto())
				   .build();
	   };
	   // Employee --> EmployeeEntity
	   private static final Converter<Employee,EmployeeEntity> employee2EmployeeEntity
	   = contex -> {
		   var employee = contex.getSource();
		   var fullname = employee.getFullname();
		   var emp = new EmployeeEntity();
		   emp.setKimlikNo(employee.getKimlikNo().getValue());
		   emp.setFullname(fullname.getFirstName()+" "+fullname.getLastName());
		   emp.setSalary(employee.getSalary().getValue());
		   emp.setBirthYear(employee.getBirthYear().getValue());
		   emp.setIban(employee.getIban().getValue());
		   emp.setDepartment(employee.getDepartment());
		   emp.setJobType(employee.getJobType());
		   emp.setPhoto(employee.getPhoto().getData());
		   return emp;
	   };
}
