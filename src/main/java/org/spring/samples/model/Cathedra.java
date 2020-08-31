package org.spring.samples.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
 * Simple JavaBean domain object representing an cathedra.
 **/

@Entity
@Table(name = "cathedras")
@JsonSerialize(using = UnitEntitySerializer.class)
public class Cathedra extends UnitEntity {

  @NotEmpty
  @Column(name = "programs")
  private String programs;

  @NotEmpty
  @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  @NotEmpty
  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;

  @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
  private Set<Group_class> group_classes;

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public String getPrograms() {
    return programs;
  }

  public void setPrograms(String programs) {
    this.programs = programs;
  }

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
