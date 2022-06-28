package com.example.employeemanager.dao;

import com.example.employeemanager.models.Working;

public interface WorkingDAO {
    public Boolean addWorking(Working working);

    public Boolean deleteWorking(int id);

    public Boolean isExist(Working newWorking);
}
