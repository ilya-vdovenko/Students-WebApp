package org.spring.samples.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@NotEmpty
@Table(name = "students")
public class Student extends Person {

    @DateTimeFormat(pattern = "dd.MM.yyyy")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_class_id")
    private Group_class group_class;

    @Column(name = "fos")
    private String fos;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getFact_address() {
        return fact_address;
    }

    public void setFact_address(String fact_address) {
        this.fact_address = fact_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Group_class getGroup_class() {
        return group_class;
    }

    public void setGroup_class(Group_class group_class) {
        this.group_class = group_class;
    }

    public String getFos() {
        return fos;
    }

    public void setFos(String fos) {
        this.fos = fos;
    }
}