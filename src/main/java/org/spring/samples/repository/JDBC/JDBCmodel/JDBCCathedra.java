package org.spring.samples.repository.JDBC.JDBCmodel;

import org.spring.samples.model.Cathedra;

public class JDBCCathedra extends Cathedra {

  private Integer boss_id;
  private Integer faculty_id;

  public Integer getBoss_id() {
    return boss_id;
  }

  public void setBoss_id(int boss_id) {
    this.boss_id = boss_id;
  }

  public Integer getFaculty_id() {
    return faculty_id;
  }

  public void setFaculty_id(int faculty_id) {
    this.faculty_id = faculty_id;
  }
}
