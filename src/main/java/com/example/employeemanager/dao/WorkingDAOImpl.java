package com.example.employeemanager.dao;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.models.Working;
import com.example.employeemanager.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WorkingDAOImpl implements WorkingDAO{
    SessionFactory sessionFactory= HibernateUtil.getInstance().getSessionFactory();
    @Override
    public Boolean addWorking(Working working) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.save(working);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
            return true;
        }
    }

    @Override
    public Boolean deleteWorking(int id) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            List<Working> workings=session.createQuery("from Working where idEmp="+id).list();
            for(Working x: workings) session.delete(x);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public Boolean isExist(Working newWorking) {
        Session session=sessionFactory.openSession();
        Transaction tx=null;
        Working working=null;
        try {
            tx=session.beginTransaction();
            working=session.get(Working.class,newWorking);
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
            if(working!=null) return true;
            return false;
        }
    }
}
