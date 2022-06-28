package com.example.employeemanager.controller;

import com.example.employeemanager.dao.EmployeeDAO;
import com.example.employeemanager.dao.EmployeeDAOImpl;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Working;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("")
public class employeeController {

    private EmployeeDAO employeeDAO=new EmployeeDAOImpl();

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<Result> getAllEmployee(){
        Result result=employeeDAO.getAllEmployee();
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result> getEmployee(@PathVariable("id") int id){
        Result result=employeeDAO.getEmployee(id);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<Result> addEmployee(@RequestBody Employee employee){
        Result result=employeeDAO.addEmployee(employee);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Result> deleteEmployee(@PathVariable("id") int id){
        Result result=employeeDAO.deleteEmployee(id);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Result> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        Result result=employeeDAO.updateEmployee(id, employee);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/working/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result> getWorking(@PathVariable("id") int id){
       Result result=employeeDAO.getWorking(id);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }


    @RequestMapping(value = "/addWorking/{id}", method = RequestMethod.POST)
    public ResponseEntity<Result> addWorking(@PathVariable("id") int id, @RequestBody Working working){
        Result result=employeeDAO.addWorking(id, working);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/salary/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result> getSalary(@PathVariable("id") int id){
        Result result=employeeDAO.getSalary(id);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }
}
