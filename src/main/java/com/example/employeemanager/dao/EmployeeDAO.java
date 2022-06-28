package com.example.employeemanager.dao;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Working;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeDAO {
    public Result getAllEmployee();
    public Result addEmployee(Employee employee);
//    public List<Employee> getEmployeesByIdTeam(int idTeam);
    public Result deleteEmployee(int id);

    public Result updateEmployee(int id, Employee newEmployee);

    public Result getEmployee(int id);

    public Result getWorking(int id);

    public Result addWorking(int id, Working working);

    public Result getSalary(int id);
}
