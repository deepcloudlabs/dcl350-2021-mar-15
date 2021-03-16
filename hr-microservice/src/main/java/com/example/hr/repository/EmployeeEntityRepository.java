package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	List<EmployeeDocument> findAllByBirthYearBetween(int fromYear,int toYear);
	@Query("select e from EmployeeEntity e where e.birthYear between :fromYear and :toYear")
	//@Query(value="select e from employees e where e.birth_year between :fromYear and :toYear",nativeQuery = true)
	List<EmployeeDocument> bulGetir(int fromYear,int toYear);
	List<EmployeeDocument> findAllByDepartment(String department);
}
