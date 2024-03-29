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
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
 * Test class for {@link StudentController}
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig({MvcAppConfig.class, MvcTestConfig.class})
class StudentControllerTests {

  private final int TEST_STUDENT_ID = 1;
  private final LocalDate birthday = LocalDate.parse("1994-09-26");
  private final Faculty faculty = new Faculty();
  private final Cathedra cathedra = new Cathedra();
  private final GroupClass groupClass = new GroupClass();

  @Autowired
  private StudentController studentController;

  @Autowired
  private InstituteService service;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    faculty.setId(1);
    cathedra.setId(1);
    cathedra.setFaculty(faculty);
    groupClass.setId(1);
    groupClass.setCathedra(cathedra);

    Student ilya = new Student();
    ilya.setId(TEST_STUDENT_ID);
    ilya.setFullName("Вдовенко Илья Сергеевич");
    ilya.setBirthday(birthday);
    ilya.setSex("муж");
    ilya.setActualAddress("г.Воронеж, Московский пр-кт 141");
    ilya.setAddress("-");
    ilya.setTelephone("89618729234");
    ilya.setFaculty(faculty);
    ilya.setCathedra(cathedra);
    ilya.setGroupClass(groupClass);

    given(this.service.findStudentById(TEST_STUDENT_ID)).willReturn(ilya);
    given(this.service.findFacultyById(anyInt())).willReturn(faculty);
    given(this.service.findCathedraById(anyInt())).willReturn(cathedra);
    given(this.service.findGroupClassById(anyInt())).willReturn(groupClass);
  }

  @Test
  void testShowStudent() throws Exception {
    mockMvc.perform(get("/students/{studentId}", TEST_STUDENT_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student"))
        .andExpect(model().attribute("student", hasProperty("fullName", is("Вдовенко Илья Сергеевич"))))
        .andExpect(model().attribute("student", hasProperty("birthday", is(birthday))))
        .andExpect(model().attribute("student", hasProperty("sex", is("муж"))))
        .andExpect(model().attribute("student",
            hasProperty("actualAddress", is("г.Воронеж, Московский пр-кт 141"))))
        .andExpect(model().attribute("student", hasProperty("address", is("-"))))
        .andExpect(model().attribute("student", hasProperty("telephone", is("89618729234"))))
        .andExpect(model().attribute("student", hasProperty("faculty", is(faculty))))
        .andExpect(model().attribute("student", hasProperty("cathedra", is(cathedra))))
        .andExpect(model().attribute("student", hasProperty("groupClass", is(groupClass))))
        .andExpect(view().name("student/studentProfile"));
  }

  @Test
  void testShowAllStudents() throws Exception {
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student_list"))
        .andExpect(view().name("student/studentList"));
  }

  @Test
  void testInitCreationForm() throws Exception {
    mockMvc.perform(get("/students/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("studentDto"))
        .andExpect(view().name("student/StudentCreateOrUpdateForm"));
  }

  @Test
  void testProcessCreationFormSuccess() throws Exception {
    mockMvc.perform(post("/students/new")
        .param("fullName", "Иванов Иван Иванович")
        .param("birthday", "1994-06-23")
        .param("sex", "муж")
        .param("actualAddress", "г.Воронеж, Московский проспект, 179в")
        .param("address", "-")
        .param("telephone", "89081355694")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().is3xxRedirection());
  }

  @Test
  void testProcessCreationFormHasErrors() throws Exception {
    mockMvc.perform(post("/students/new")
        .param("fullName", "Иванов Иван Иванович")
        .param("birthday", "")
        .param("sex", "муж")
        .param("telephone", "8903456")
        .param("actualAddress", "ул. НетНазвания")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().isOk())
        .andExpect(model().attributeHasErrors("studentDto"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "birthday"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "actualAddress"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "telephone"))
        .andExpect(view().name("student/StudentCreateOrUpdateForm"));
  }

  @Test
  void testInitUpdateStudentForm() throws Exception {
    mockMvc.perform(get("/students/{studentId}/edit", TEST_STUDENT_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("studentDto"))
        .andExpect(
            model().attribute("studentDto", hasProperty("fullName", is("Вдовенко Илья Сергеевич"))))
        .andExpect(model().attribute("studentDto", hasProperty("birthday", is(birthday))))
        .andExpect(model().attribute("studentDto", hasProperty("sex", is("муж"))))
        .andExpect(model().attribute("studentDto",
            hasProperty("actualAddress", is("г.Воронеж, Московский пр-кт 141"))))
        .andExpect(model().attribute("studentDto", hasProperty("address", is("-"))))
        .andExpect(model().attribute("studentDto", hasProperty("telephone", is("89618729234"))))
        .andExpect(model().attribute("studentDto", hasProperty("faculty", is(faculty))))
        .andExpect(model().attribute("studentDto", hasProperty("cathedra", is(cathedra))))
        .andExpect(model().attribute("studentDto", hasProperty("groupClass", is(groupClass))))
        .andExpect(view().name("student/StudentCreateOrUpdateForm"));
  }

  @Test
  void testProcessUpdateStudentFormSuccess() throws Exception {
    mockMvc.perform(post("/students/{studentId}/edit", TEST_STUDENT_ID)
        .param("fullName", "Вдовенко Илья Сергеевич")
        .param("birthday", "1994-09-26")
        .param("sex", "жен")
        .param("actualAddress", "г.Воронеж, Московский пр-кт 141")
        .param("address", "-")
        .param("telephone", "89518739244")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().is3xxRedirection());
  }

  @Test
  void testProcessUpdateStudentFormHasErrors() throws Exception {
    mockMvc.perform(post("/students/{studentId}/edit", TEST_STUDENT_ID)
        .param("fullName", "Иванов Иван Иванович")
        .param("birthday", "")
        .param("sex", "муж")
        .param("telephone", "8903456")
        .param("actualAddress", "ул. НетНазвания")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().isOk())
        .andExpect(model().attributeHasErrors("studentDto"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "birthday"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "actualAddress"))
        .andExpect(model().attributeHasFieldErrors("studentDto", "telephone"))
        .andExpect(view().name("student/StudentCreateOrUpdateForm"));
  }
}
