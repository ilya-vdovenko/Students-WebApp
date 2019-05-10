package org.spring.samples.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "Group_classes")
public class Group_class extends BaseEntity {

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cathedra_id")
    private Cathedra cathedra;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "group_class", fetch = FetchType.EAGER)
    private Set<Student> group_students;

    @NotEmpty
    @Column(name = "fos")
    private String fos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Student> getGroup_students() {
        return group_students;
    }

    public void setGroup_students(Set<Student> group_students) {
        this.group_students = group_students;
    }

    public String getFos() {
        return fos;
    }

    public void setFos(String fos) {
        this.fos = fos;
    }
}