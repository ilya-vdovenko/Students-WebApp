/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * Simple JavaBean domain object representing a group class.
 *
 * @author Ilya Vdovenko
 */

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
      ", cathedra id=" + cathedra.getId() +
      ", group_students=" + group_students +
      '}';
  }
}
