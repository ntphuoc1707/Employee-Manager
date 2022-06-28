package com.example.employeemanager.controller;

import com.example.employeemanager.dao.EmployeeDAO;
import com.example.employeemanager.dao.EmployeeDAOImpl;
import com.example.employeemanager.dao.TeamDAO;
import com.example.employeemanager.dao.TeamDAOImpl;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class teamController {

    private TeamDAO teamDAO=new TeamDAOImpl();
    private EmployeeDAO employeeDAO=new EmployeeDAOImpl();

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public ResponseEntity<Result> getAllTeam(){
        Result result=teamDAO.getAllTeam();
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

    @RequestMapping(value = "/addTeam", method = RequestMethod.POST)
    public ResponseEntity<Result> addTeam(@RequestBody Team team){
        Result result=teamDAO.addTeam(team);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }

//    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Result> getEmployeesOfTeam(@PathVariable("id") int id){
//        List<Employee> employees =employeeDAO.getEmployeesByIdTeam(id);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new Result(
//                        true,
//                        employees,
//                        "Get all team successfully"
//                )
//        );
//    }

    @RequestMapping(value = "/team/{id}/employees", method = RequestMethod.GET)
    public ResponseEntity<Result> getEmployeesFromTeam(@PathVariable("id") int id){
        Result result=teamDAO.getEmployeesFromTeam(id);
        return ResponseEntity.status(result.getStatus()?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .body(result);
    }
}
