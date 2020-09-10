package org.spring.samples.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Simple JavaBean domain object representing an faculty.
 **/

@Entity
@Table(name = "faculties")
@JsonSerialize(using = UnitEntitySerializer.class)
public class Faculty extends UnitEntity {

  @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
  private Set<Cathedra> cathedras;

  @NotEmpty
  @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  public Set<Cathedra> getCathedras() {
    return cathedras;
  }

  public void setCathedras(Set<Cathedra> cathedras) {
    this.cathedras = cathedras;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }
}
