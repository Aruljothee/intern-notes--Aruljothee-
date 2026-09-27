# Demo 1 – Employee Management Application

## EmployeeRepository.java

```java
package com.example.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
```

## Employee.java

```java
package com.example.employee_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String department;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
```

## EmployeeController.java

```java
package com.example.employee_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get one employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    // Update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {

        boolean deleted = employeeService.deleteEmployee(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
```

## EmployeeService.java

```java
package com.example.employee_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    // Delete employee
    public boolean deleteEmployee(int id) {

        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }
}
```

## SecurityConfig.java

```java
package com.example.employee_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
```

## EmployeeManagementApplication.java

```java
package com.example.employee_management; 

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication 
public class EmployeeManagementApplication { 

	public static void main(String[] args) { 
		SpringApplication.run(EmployeeManagementApplication.class, args); 
	} 

}
```

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"> 
	<modelVersion>4.0.0</modelVersion> 
	<parent> 
		<groupId>org.springframework.boot</groupId> 
		<artifactId>spring-boot-starter-parent</artifactId> 
		<version>4.1.1</version> 
		<relativePath/> <!-- lookup parent from repository --> 
	</parent> 
	<groupId>com.example</groupId> 
	<artifactId>employee-management</artifactId> 
	<version>0.0.1-SNAPSHOT</version> 
	<name/> 
	<description/> 
	<url/> 
	<licenses> 
		<license/> 
	</licenses> 
	<developers> 
		<developer/> 
	</developers> 
	<scm> 
		<connection/> 
		<developerConnection/> 
		<tag/> 
		<url/> 
	</scm> 
	<properties> 
		<java.version>17</java.version> 
	</properties> 
	<dependencies> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-data-jpa</artifactId> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-security</artifactId> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-validation</artifactId> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-webmvc</artifactId> 
		</dependency> 

		<dependency> 
			<groupId>com.mysql</groupId> 
			<artifactId>mysql-connector-j</artifactId> 
			<scope>runtime</scope> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-data-jpa-test</artifactId> 
			<scope>test</scope> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-security-test</artifactId> 
			<scope>test</scope> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-validation-test</artifactId> 
			<scope>test</scope> 
		</dependency> 
		<dependency> 
			<groupId>org.springframework.boot</groupId> 
			<artifactId>spring-boot-starter-webmvc-test</artifactId> 
			<scope>test</scope> 
		</dependency> 
	</dependencies> 

	<build> 
		<plugins> 
			<plugin> 
				<groupId>org.springframework.boot</groupId> 
				<artifactId>spring-boot-maven-plugin</artifactId> 
			</plugin> 
		</plugins> 
	</build> 

</project>
```

## application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db

spring.datasource.username=root

spring.datasource.password=Vallalar@008

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true
```
