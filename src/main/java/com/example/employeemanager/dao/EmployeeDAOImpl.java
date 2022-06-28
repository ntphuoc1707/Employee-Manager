package com.example.employeemanager.dao;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Working;
import com.example.employeemanager.util.HibernateUtil;
import com.mysql.cj.conf.ConnectionUrlParser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.util.Pair;

import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    SessionFactory sessionFactory= HibernateUtil.getInstance().getSessionFactory();
    TeamDAO teamDAO=new TeamDAOImpl();

    WorkingDAO workingDAO=new WorkingDAOImpl();
    @Override
    public Result getAllEmployee() {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        List<Employee> employees=new ArrayList<>();
        try {
            tx=session.beginTransaction();
            employees=session.createQuery("FROM Employee ").list();
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false,null,e.getMessage());
        }
        finally {
            return new Result(true,employees,"Get all employee successfully");
        }
    }

    @Override
    public Result addEmployee(Employee employee) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            System.out.println(employee.toString());
            if(!teamDAO.isExist(employee.getIdTeam()) && employee.getIdTeam()!=0)
                return new Result(false,null,"Team "+employee.getIdTeam()+" doesn't exist. If employee don't belong to any team, default is 0");
            session.save(employee);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false,null,e.getMessage());
        }
        return new Result(true,null,"Add employee successfully");
    }

//    @Override
//    public List<Employee> getEmployeesByIdTeam(int idTeam) {
//        Session session=sessionFactory.openSession();
//        Transaction tx=null;
//        List<Employee> employees=new ArrayList<>();
//        try {
//            tx=session.beginTransaction();
//            employees=session.createQuery("FROM Employee where idTeam="+idTeam).list();
//            tx.commit();
//        }catch (Exception e){
//            if(tx!=null) tx.rollback();
//            e.printStackTrace();
//            return null;
//        }
//        finally {
//            return employees;
//        }
//    }

    @Override
    public Result deleteEmployee(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            Employee employee=session.get(Employee.class,id);
            if(employee!=null){
                workingDAO.deleteWorking(id);
                session.delete(employee);
            }
            else return new Result(false,null,"Employee "+id+" doesn't exist");
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false, null, e.getMessage());
        }
        return new Result(true,null, "Delete employee "+id+" successfully");

    }

    @Override
    public Result updateEmployee(int id, Employee newEmployee) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            Employee employee=session.get(Employee.class,id);
            if(employee!=null){
                if(newEmployee.getName()!=null) employee.setName(newEmployee.getName());
                if(newEmployee.getAddress()!=null) employee.setAddress(newEmployee.getAddress());
                if(newEmployee.getBirthday()!=null) employee.setBirthday(newEmployee.getBirthday());
                if(newEmployee.getGender()!=null) employee.setGender(newEmployee.getGender());
                if(newEmployee.getPhone()!=null) employee.setPhone(newEmployee.getPhone());
                if(Double.valueOf(newEmployee.getSalaryPerHour())!=null) employee.setSalaryPerHour(newEmployee.getSalaryPerHour());
                System.out.println(teamDAO.isExist(newEmployee.getIdTeam()));
                if(teamDAO.isExist(newEmployee.getIdTeam())) employee.setIdTeam(newEmployee.getIdTeam());
                session.update(employee);
            }
            else return new Result(false,null,"Employee "+id+" doesn't exist");
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false, null, e.getMessage());
        }
        finally {
           session.close();
        }
        return new Result(true, null, "Update employee "+id+" successfully");
    }

    @Override
    public Result getEmployee(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        Employee employee=null;
        try {
            tx=session.beginTransaction();
            employee=session.get(Employee.class,id);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false, null, e.getMessage());
        }
        finally {
            session.close();
            if(employee==null) return new Result(false,null,"Employee "+id+" doesn't exist");
            else return new Result(true, employee, "Get employee "+id+" successfully");
        }
    }

    @Override
    public Result getWorking(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        Employee employee=null;
        try {
            tx=session.beginTransaction();
            employee=session.get(Employee.class,id);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false,null,e.getMessage());
        }
        finally {
            session.close();
            if (employee != null) {
                if (!employee.getWorking().isEmpty())
                    return new Result(true, employee.getWorking(), "Get working's employee " + id + " successfully");
                else
                    return new Result(false, null, "Don't see any working");
            }
            else return new Result(false,null, "Employee doesn't exist");
        }
    }

    @Override
    public Result addWorking(int id, Working working) {
        working.setIdEmp(id);
        if(getEmployee(id).getStatus()){
            if(!workingDAO.isExist(working))
                return new Result(true, workingDAO.addWorking(working),"Add working successfully");
            return new Result(false,null,"Working already exist");
        }
        else return new Result(false, null, "Employee "+id+" doesn't exist");
    }

    @Override
    public Result getSalary(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        Employee employee=null;
        List<Map<String,Double>> salary=new ArrayList<>();
        try {
            tx=session.beginTransaction();
            employee=session.get(Employee.class,id);
            for(Working x : employee.getWorking())
            {
                Map<String,Double> temp=new HashMap<>();
                temp.put(x.getDate(),x.getHour()*employee.getSalaryPerHour());
                salary.add(temp);
            }
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false, null, e.getMessage());
        }
        finally {
            session.close();
            if(!salary.isEmpty())
                return new Result(true, salary, "Get salary successfully");
            else return new Result(false,null, "Don't see any work");
        }
    }

}
