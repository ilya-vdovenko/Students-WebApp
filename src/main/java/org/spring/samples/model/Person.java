package org.spring.samples.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an person.
 * Used as a base class for objects needing this property.
 *
 **/

@MappedSuperclass
public class Person extends BaseEntity {

    @NotEmpty
    @Column(name = "fio")
    private String fio;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cathedra_id")
    private Cathedra cathedra;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }
}