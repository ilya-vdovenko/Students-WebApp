package org.spring.samples.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing an student.
 **/

@Entity
@NotEmpty
@Table(name = "students")
public class Student extends Person {

  @Column(name = "birthday")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;

  @Column(name = "sex")
  private String sex;

  @Column(name = "fact_address")
  private String fact_address;

  @Column(name = "address")
  private String address;

  @Column(name = "telephone")
  @Digits(fraction = 0, integer = 10)
  private String telephone;

  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "group_class_id")
  private Group_class group_class;

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getFact_address() {
    return fact_address;
  }

  public void setFact_address(String fact_address) {
    this.fact_address = fact_address;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Group_class getGroup_class() {
    return group_class;
  }

  public void setGroup_class(Group_class group_class) {
    this.group_class = group_class;
  }

  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", birthday=" + birthday +
      ", sex='" + sex + '\'' +
      ", fact_address='" + fact_address + '\'' +
      ", address='" + address + '\'' +
      ", telephone='" + telephone + '\'' +
      ", group_class id=" + group_class.getId() +
      ", cathedra id=" + getCathedra().getId() +
      ", faculty id=" + getFaculty().getId() +
      '}';
  }
}
