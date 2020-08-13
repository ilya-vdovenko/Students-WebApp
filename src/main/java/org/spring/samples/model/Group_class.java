package org.spring.samples.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an group_class.
 **/

@Entity
@Table(name = "group_classes")
public class Group_class extends BaseEntity {

  @NotEmpty
  @Column(name = "number")
  private String number;

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

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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
