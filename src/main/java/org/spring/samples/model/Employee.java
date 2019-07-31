package org.spring.samples.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an Employee.
 *
 **/

@Entity
@Table(name = "employees")
public class Employee extends Person {

    @NotEmpty
    @Column(name = "position")
    private String position;

    @Column(name = "degree")
    private String degree;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}