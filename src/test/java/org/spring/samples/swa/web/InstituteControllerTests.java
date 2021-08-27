/*
 * Copyright 2019-2021, Ilya Vdovenko and the Students-WebApp contributors.
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

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.samples.swa.config.MvcTestConfig;
import org.spring.samples.swa.config.MvcAppConfig;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for {@link InstituteController}.
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig({MvcAppConfig.class, MvcTestConfig.class})
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
    GroupClass groupClass = new GroupClass();
    groupClass.setId(TEST_GROUP_ID);
    groupClass.setTitle("ПТ-111");

    faculty.setCathedras(new HashSet<>(Lists.newArrayList(cathedra)));
    cathedra.setGroupClasses(new HashSet<>(Lists.newArrayList(groupClass)));
    groupClass.setGroupStudents(new HashSet<>(Lists.newArrayList(new Student())));

    given(this.service.findFacultyById(TEST_FACULTY_ID)).willReturn(faculty);
    given(this.service.findCathedraById(TEST_CATHEDRA_ID)).willReturn(cathedra);
    given(this.service.findGroupClassById(TEST_GROUP_ID)).willReturn(groupClass);

    given(this.service.getFacultyBoard(anySet(), anyInt())).willReturn(new ArrayList<>());
    given(this.service.getFacultyEmployees(anySet(), anyInt())).willReturn(new ArrayList<>());
    given(this.service.getCathedraLecturers(anySet(), anyInt())).willReturn(new ArrayList<>());
  }

  @Test
  void testShowFaculty() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}", TEST_FACULTY_ID))
        .andExpect(status().isOk())
        .andExpect(model()
            .attribute("faculty", hasProperty("title", is("Энергетики и систем управления"))))
        .andExpect(view().name("institute/facultyProfile"));
  }

  @Test
  void testShowAllFaculties() throws Exception {
    mockMvc.perform(get("/faculties"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("faculty_list"))
        .andExpect(view().name("institute/facultyList"));
  }

  @Test
  void testShowCathedra() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}", TEST_CATHEDRA_ID))
        .andExpect(status().isOk())
        .andExpect(model().attribute("cathedra",
            hasProperty("title", is("Электромеханических систем и электроснабжения"))))
        .andExpect(view().name("institute/cathedraProfile"));
  }

  @Test
  void testShowAllCathedras() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/cathedras", TEST_FACULTY_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("faculty", "cathedra_list"))
        .andExpect(view().name("institute/cathedraList"));
  }

  @Test
  void testShowGroup() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/*/groupClasses/{groupClassId}", TEST_GROUP_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("groupClass", "group_students_list"))
        .andExpect(view().name("institute/groupClassProfile"));
  }

  @Test
  void testShowAllGroup() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}/groupClasses", TEST_CATHEDRA_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("cathedra", "group_class_list"))
        .andExpect(view().name("institute/groupClassList"));
  }

  @Test
  void testShowFacultyEmployees() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/employees", TEST_FACULTY_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("faculty", "employee_list", "board"))
        .andExpect(model().attribute("board", false))
        .andExpect(view().name("institute/unitEmployees"));
  }

  @Test
  void testShowFacultyBoard() throws Exception {
    mockMvc.perform(get("/faculties/{facultyId}/board", TEST_FACULTY_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("faculty", "employee_list", "board"))
        .andExpect(model().attribute("board", true))
        .andExpect(view().name("institute/unitEmployees"));
  }

  @Test
  void testShowCathedraLecturers() throws Exception {
    mockMvc.perform(get("/faculties/*/cathedras/{cathedraId}/lecturers", TEST_CATHEDRA_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("cathedra", "employee_list"))
        .andExpect(view().name("institute/unitEmployees"));
  }
}
