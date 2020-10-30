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

package org.spring.samples.swa.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test class for {@link InstituteController}
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig(locations = {"classpath:SpringConfigs/mvc-config.xml", "classpath:SpringConfigs/mvc-test-config.xml"})
class InstituteControllerTests {

  private final int TEST_FACULTY_ID = 1;
  private final int TEST_CATHEDRA_ID = 2;
  private final int TEST_GROUP_ID = 3;

  @Autowired
  private InstituteController instituteController;

  @Autowired
  private InstituteService service;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(instituteController).build();
    Faculty faculty = new Faculty();
    faculty.setId(TEST_FACULTY_ID);
    faculty.setTitle("Энергетики и систем управления");
    Cathedra cathedra = new Cathedra();
    cathedra.setId(TEST_CATHEDRA_ID);
    cathedra.setTitle("Электромеханических систем и электроснабжения");
    Group_class group_class = new Group_class();
    group_class.setId(TEST_GROUP_ID);
    group_class.setTitle("ПТ-111");

    faculty.setCathedras(new HashSet<>(Lists.newArrayList(cathedra)));
    cathedra.setGroup_classes(new HashSet<>(Lists.newArrayList(group_class)));
    group_class.setGroup_students(new HashSet<>(Lists.newArrayList(new Student())));

    given(this.service.findFacultyById(TEST_FACULTY_ID)).willReturn(faculty);
    given(this.service.findCathedraById(TEST_CATHEDRA_ID)).willReturn(cathedra);
    given(this.service.findGroup_classById(TEST_GROUP_ID)).willReturn(group_class);

    given(this.service.getFacultySoviet(anySet(), anyInt())).willReturn(new ArrayList<>());
    given(this.service.getFacultyEmployees(anySet(), anyInt())).willReturn(new ArrayList<>());
    given(this.service.getCathedraLecturers(anySet(), anyInt())).willReturn(new ArrayList<>());
  }

  @Test
  void testShowFaculty() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}", TEST_FACULTY_ID))
      .andExpect(status().isOk())
      .andExpect(model().attribute("faculty", hasProperty("title", is("Энергетики и систем управления"))))
      .andExpect(view().name("facultyProfile"));
  }

  @Test
  void testShowAllFaculties() throws Exception {
    mockMvc.perform(get("/faculties"))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("faculty_list"))
      .andExpect(view().name("facultyList"));
  }

  @Test
  void testShowCathedra() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}", TEST_CATHEDRA_ID))
      .andExpect(status().isOk())
      .andExpect(model().attribute("cathedra", hasProperty("title", is("Электромеханических систем и электроснабжения"))))
      .andExpect(view().name("cathedraProfile"));
  }

  @Test
  void testShowAllCathedras() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/cathedras", TEST_FACULTY_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("faculty", "cathedra_list"))
      .andExpect(view().name("cathedraList"));
  }

  @Test
  void testShowGroup() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/*/group_classes/{group_classId}", TEST_GROUP_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("group_class", "group_students_list"))
      .andExpect(view().name("group_classProfile"));
  }

  @Test
  void testShowAllGroup() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}/group_classes", TEST_CATHEDRA_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("cathedra", "group_class_list"))
      .andExpect(view().name("group_classList"));
  }

  @Test
  void testShowFacultyEmployees() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/employees", TEST_FACULTY_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("faculty", "employee_list", "soviet"))
      .andExpect(model().attribute("soviet", false))
      .andExpect(view().name("employeeList"));
  }

  @Test
  void testShowFacultySoviet() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/soviet", TEST_FACULTY_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("faculty", "employee_list", "soviet"))
      .andExpect(model().attribute("soviet", true))
      .andExpect(view().name("employeeList"));
  }

  @Test
  void testShowCathedraLecturers() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}/lecturers", TEST_CATHEDRA_ID))
      .andExpect(status().isOk())
      .andExpect(model().attributeExists("cathedra", "employee_list"))
      .andExpect(view().name("employeeList"));
  }
}
