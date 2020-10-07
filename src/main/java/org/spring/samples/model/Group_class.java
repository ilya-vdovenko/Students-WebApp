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
 * Simple JavaBean domain object representing an group_class.
 **/

@Entity
@Table(name = "group_classes")
@JsonSerialize(using = Group_classSerializer.class)
public class Group_class extends BaseEntity {

  @NotEmpty
  @Column(name = "title")
  private String title;

  @NotEmpty
  @Column(name = "edu_form")
  private String eduForm;

  @NotEmpty
  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "cathedra_id")
  private Cathedra cathedra;

  @OneToMany(mappedBy = "group_class", fetch = FetchType.EAGER)
  private Set<Student> group_students;

  public String getTitle() {
    return title;
  }

  public void setTitle(String number) {
    this.title = number;
  }

  public Cathedra getCathedra() {
    return cathedra;
  }

  public void setCathedra(Cathedra cathedra) {
    this.cathedra = cathedra;
  }

  public Set<Student> getGroup_students() {
    return group_students;
  }

  public void setGroup_students(Set<Student> group_students) {
    this.group_students = group_students;
  }

  public String getEduForm() {
    return eduForm;
  }

  public void setEduForm(String fos) {
    this.eduForm = fos;
  }

  @Override
  public String toString() {
    return "Group_class{" +
      ", id=" + id +
      ", title='" + title + '\'' +
      ", eduForm='" + eduForm + '\'' +
      ", cathedra=" + cathedra +
      ", group_students=" + group_students +
      '}';
  }
}
