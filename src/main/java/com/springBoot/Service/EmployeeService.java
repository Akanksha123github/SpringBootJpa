package com.springBoot.Service;

import java.util.List;

import com.springBoot.entity.Employee;

public interface EmployeeService {
	
	Employee save(Employee employee);
	
	Employee getEmpById(int id);
	
	List<Employee> getAllEmp();
	
	int updateById(int id, Employee employee);
	
	String deleteById(int id);
	
	List<Employee> getAllEmpByPage(int page , int size);
	
	List<Employee> getAllEmpBySorting();

}
