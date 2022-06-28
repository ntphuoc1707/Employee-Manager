package com.example.employeemanager.dao;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Result;
import com.example.employeemanager.models.Team;
import com.example.employeemanager.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TeamDAOImpl implements  TeamDAO{
    SessionFactory sessionFactory= HibernateUtil.getInstance().getSessionFactory();

    @Override
    public Result getAllTeam() {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        List<Team> teams=new ArrayList<>();
        try {
            tx=session.beginTransaction();
            teams=session.createQuery("FROM Team ").list();
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false,null,e.getMessage());
        }
        finally {
            session.close();
            if(teams.size()==0) return new Result(true, null, "Don't have any team");
            else return new Result(true,teams, "Get all team successfully");
        }
    }

    @Override
    public Result addTeam(Team team) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            if(!findTeambyName(team.getName()).getStatus())
                session.save(team);
            else return new Result(false,null,"Team's name already exist");
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false, null,e.getMessage());
        }
        finally {
            session.close();
        }
        return new Result(true, null, "Add team successfully");
    }

    @Override
    public Result getEmployeesFromTeam(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        Team team=null;
        try {
            tx=session.beginTransaction();
            team=session.get(Team.class,id);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
           return new Result(false,null,e.getMessage());
        }
        finally {
            session.close();
            if(team.getEmployees().size()==0) return new Result(true,null,"Team don't have member");
            return new Result(true, team.getEmployees(),"Get team's employees successfully");
        }
    }

    @Override
    public Boolean isExist(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        List<Team> team=null;
        try {
            tx=session.beginTransaction();
            team=session.createQuery("from Team where id="+id).list();
            System.out.println(team);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
            if(team.size()!=0)
                return true;
            else return false;
        }
    }

    @Override
    public Result findTeambyName(String name) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        List<Team> team=null;
        try {
            tx=session.beginTransaction();
            team=session.createQuery("from Team where name='"+name+"'").list();
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return new Result(false,null,e.getMessage());
        }
        finally {
            session.close();
            if(team.isEmpty()) return new Result(false,null,"Team doesn't exist");
            else return new Result(true, team,"Get team by name successfully");
        }
    }
}
