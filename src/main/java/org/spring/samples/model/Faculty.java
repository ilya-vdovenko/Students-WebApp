package org.spring.samples.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "faculties")
public class Faculty extends UnitEntity {

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Employee> soviet;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Cathedra> cathedras;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Student> students;

    @NotEmpty
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Group_class> group_classes;

    public Set<Employee> getSoviet() {
        return soviet;
    }

    public void setSoviet(Set<Employee> soviet) {
        this.soviet = soviet;
    }

    public Set<Cathedra> getCathedras() {
        return cathedras;
    }

    public void setCathedras(Set<Cathedra> cathedras) {
        this.cathedras = cathedras;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Group_class> getGroup_classes() {
        return group_classes;
    }

    public void setGroup_classes(Set<Group_class> group_classes) {
        this.group_classes = group_classes;
    }
}