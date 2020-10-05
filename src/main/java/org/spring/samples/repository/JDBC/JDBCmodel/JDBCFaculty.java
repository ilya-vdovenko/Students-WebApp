package org.spring.samples.repository.JDBC.JDBCmodel;

import org.spring.samples.model.Faculty;

public class JDBCFaculty extends Faculty {

  private Integer boss_id;

  public Integer getBoss_id() {
    return boss_id;
  }

  public void setBoss_id(int boss_id) {
    this.boss_id = boss_id;
  }
}
