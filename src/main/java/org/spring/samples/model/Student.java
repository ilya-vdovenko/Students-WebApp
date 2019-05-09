package org.spring.samples.model;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "students")
@NotEmpty
public class Student extends BaseEntity {

    @Column(name = "fio")
    private String fio;

    //TODO: в jsp формат даты с временем. Убрать.
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "sex")
    private char sex;

    @Column(name = "fact_address")
    private String fact_address;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "group_class")
    private String group_class;

    @Column(name = "fos")
    private String fos;

    public String getFio() {
        return fio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public char getSex() {
        return sex;
    }

    public String getFact_address() {
        return fact_address;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getGroup_class() {
        return group_class;
    }

    public String getFos() {
        return fos;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setFact_address(String fact_address) {
        this.fact_address = fact_address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setGroup_class(String group_class) {
        this.group_class = group_class;
    }

    public void setFos(String fos) {
        this.fos = fos;
    }
}