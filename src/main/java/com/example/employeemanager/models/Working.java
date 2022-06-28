package com.example.employeemanager.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "working")
public class Working implements Serializable {
    @Id
    @Column(name = "id_emp")
    private int idEmp;

    @Id
    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private double hour;


    public Working() {
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

}
