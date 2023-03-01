package com.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.Service.EmployeeService;
import com.springBoot.entity.Employee;
import com.springBoot.repository.EmployeeRepository;

@RestController
@RequestMapping("emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/save")
	public Employee saveEmp(@RequestBody Employee employee) {
		return employeeservice.save(employee);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id) {
		Employee empById = employeeservice.getEmpById(id);
		return new ResponseEntity<Employee>(empById, HttpStatus.ACCEPTED) ;
	}
		
	
	@GetMapping("/request")
	public ResponseEntity<Employee> getById(@RequestParam int  id) {
		Employee empById = employeeservice.getEmpById(id);
		return new ResponseEntity<Employee>(empById, HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping(path = "/{name}/{city}")
//	public List<Employee>  test(@PathVariable String name,@PathVariable String city ) {
//		
//		//List<Employee> findByCity = employeeRepository.findByCity(city);  //---> Only for CIty
//		//List<Employee> findByNameAndCity = employeeRepository.findByNameAndCity(name, city);   //Find By City and Name
//		
//		return findByNameAndCity;
//	}
	
	@GetMapping("/{city}/{name}")
	public ResponseEntity<Employee> test(@PathVariable String city,@PathVariable String name) {
		Employee getdata = employeeRepository.getdata(city, name);
		return new ResponseEntity<Employee>(getdata, HttpStatus.OK);  //ResponseEntity used to change the status
	}
	
	
	@GetMapping("/getAll")
	public List<Employee> getAllEmp(){
		//Iterable<Employee> allEmp = employeeservice.getAllEmp();
		return employeeservice.getAllEmpBySorting();
	}
	
	
	@GetMapping("/getAllByPage/{page}")
	public List<Employee> getAllEmpByPage(@PathVariable int page ){
		Iterable<Employee> allEmp = employeeservice.getAllEmpByPage(page, 3);
		return (List<Employee>) allEmp;
	}
	
	@PutMapping("/update")
	public String getupdateById(@RequestBody Employee employee ) {
		int byId = employeeservice.updateById(employee.getId(), employee);
		
		if(byId ==0) {
			return "Id is not present";
		}
		return String.format("Id is present %d", byId);	
	}
	
	@DeleteMapping("/delete")
	public String deleteId(@RequestParam int  id) {
		
		return employeeservice.deleteById(id);
	}
}
