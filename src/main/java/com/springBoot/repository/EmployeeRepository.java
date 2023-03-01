package com.springBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBoot.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	 List<Employee> findByCity(String city);
	public List<Employee> findByNameAndCity(String name, String city);
	
	
	@Query(value="Select * from emp where city=:city and name=:name",nativeQuery = true)
	public Employee getdata(@Param(value = "city") String city,@Param(value = "name") String name);

}
