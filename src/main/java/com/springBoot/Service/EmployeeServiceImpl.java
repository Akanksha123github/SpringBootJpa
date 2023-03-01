package com.springBoot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springBoot.entity.Employee;
import com.springBoot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmpById(int id) {
		Optional<Employee> byId = employeeRepository.findById(id);
		return byId.isPresent() ? byId.get() :null;
	}

	@Override
	public List<Employee> getAllEmp() {
		Iterable<Employee> iterable = employeeRepository.findAll();
		return (List<Employee>) iterable;
		
	}

	@Override
	public int updateById(int id, Employee employee) {
		Optional<Employee> findById = employeeRepository.findById(id);
		
		if(findById.isPresent()) {
			Employee save = employeeRepository.save(employee);
			return save.getId();
		}
		return 0;
	}

	@Override
	public String deleteById(int id) {
		employeeRepository.deleteById(id);
		return "Delete Record Successfully";
	}

	@Override
	public List<Employee> getAllEmpByPage(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Employee> pageresponse = employeeRepository.findAll(pageRequest); //it will give us page of employee[Take Find Method of page]
		List<Employee> EmployeeList = pageresponse.getContent();  //It will give us List of Employee
		return EmployeeList;
	}

	@Override
	public List<Employee> getAllEmpBySorting() {
	   List<Employee> findAll = employeeRepository.findAll(Sort.by("name").ascending());  // take findAll method of sorting
		return findAll;
	}

}
