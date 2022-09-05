package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees
    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();            // why this is needed here
    }

    //get employee by empid
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value = "id") long empid) throws ResourceNotFoundException {
        Employee emp = employeeRepository.findById(empid).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :"+empid));
        return ResponseEntity.ok().body(emp);
    }

    //add an employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee emp){
         return this.employeeRepository.save(emp);
    }

    // update employee detail by id
    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long empid , @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee emp = employeeRepository.findById(empid).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :"+empid));
        emp.setName(employeeDetails.getName());
        emp.setEmail(employeeDetails.getEmail());
        emp.setTeam(employeeDetails.getTeam());
        return ResponseEntity.ok(this.employeeRepository.save(emp));
    }

    // delete employee
    @DeleteMapping("employee/{id}")
    public Map<String , Boolean> deleteEmployee(@PathVariable(value = "id") long empid) throws ResourceNotFoundException {
        Employee emp = employeeRepository.findById(empid).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :"+empid));
        this.employeeRepository.delete(emp);
        Map<String , Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return response;
    }
}
