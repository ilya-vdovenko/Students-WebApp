package org.spring.samples.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "cathedras")
public class Cathedra extends UnitEntity {

    @NotEmpty
    @Column(name = "programs")
    private String programs;

    /*@OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
    private Set<Employee> lecturers;*/

    @NotEmpty
    @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
    private Set<Group_class> group_classes;

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    /*public Set<Employee> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Employee> lecturers) {
        this.lecturers = lecturers;
    }*/

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Group_class> getGroup_classes() {
        return group_classes;
    }

    public void setGroup_classes(Set<Group_class> group_classes) {
        this.group_classes = group_classes;
    }
}
