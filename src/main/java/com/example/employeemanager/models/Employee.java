package com.example.employeemanager.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    @Column(name = "id_team")
    private int idTeam;

    @Column(name = "gender")
    private Byte gender;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @Column(name = "address")
    private String address;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_team", insertable = false, updatable = false)
    public Team team;

    public Team getTeam(){return team;}

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_emp", insertable = false, updatable = false)
    public Set<Working> working;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idTeam=" + idTeam +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", salaryPerHour=" + salaryPerHour +
                ", address='" + address + '\'' +
                ", team=" + team +
                ", working=" + working +
                '}';
    }

    public Set<Working> getWorking() {
        return working;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public Byte getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public String getAddress() {
        return address;
    }
}
