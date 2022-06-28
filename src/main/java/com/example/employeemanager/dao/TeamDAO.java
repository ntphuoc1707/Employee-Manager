package com.example.employeemanager.dao;

import com.example.employeemanager.EmployeeManagerApplication;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Team;

import java.util.List;
import java.util.Set;

public interface TeamDAO {
    public Result getAllTeam();
    public Result addTeam(Team team);

    public Result getEmployeesFromTeam(int id);

    public Boolean isExist(int id);

    public Result findTeambyName(String name);

}
