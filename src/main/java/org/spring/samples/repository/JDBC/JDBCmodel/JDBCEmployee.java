package org.spring.samples.repository.JDBC.JDBCmodel;

import org.spring.samples.model.Employee;

public class JDBCEmployee extends Employee {

  private Integer faculty_id;

  private Integer cathedra_id;

  public Integer getFaculty_id() {
    return faculty_id;
  }

  public void setFaculty_id(int faculty_id) {
    this.faculty_id = faculty_id;
  }

  public Integer getCathedra_id() {
    return cathedra_id;
  }

  public void setCathedra_id(int cathedra_id) {
    this.cathedra_id = cathedra_id;
  }
}
