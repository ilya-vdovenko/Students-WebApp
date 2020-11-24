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

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@SpringJUnitWebConfig(locations = {"classpath:SpringConfigs/mvc-config.xml",
    "classpath:SpringConfigs/mvc-test-config.xml"})
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

    Student ilya = new Student();
    ilya.setId(TEST_STUDENT_ID);
    ilya.setFio("Вдовенко Илья Сергеевич");
    ilya.setBirthday(birthday);
    ilya.setSex("муж");
    ilya.setFactAddress("г.Воронеж, Московский пр-кт 141");
    ilya.setAddress("-");
    ilya.setTelephone("89518719254");
    ilya.setFaculty(faculty);
    ilya.setCathedra(cathedra);
    ilya.setGroupClass(groupClass);

    given(this.service.findStudentById(TEST_STUDENT_ID)).willReturn(ilya);
  }

  @Test
  void testShowStudent() throws Exception {
    mockMvc.perform(get("/students/{studentId}", TEST_STUDENT_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student"))
        .andExpect(model().attribute("student", hasProperty("fio", is("Вдовенко Илья Сергеевич"))))
        .andExpect(model().attribute("student", hasProperty("birthday", is(birthday))))
        .andExpect(model().attribute("student", hasProperty("sex", is("муж"))))
        .andExpect(model().attribute("student",
            hasProperty("factAddress", is("г.Воронеж, Московский пр-кт 141"))))
        .andExpect(model().attribute("student", hasProperty("address", is("-"))))
        .andExpect(model().attribute("student", hasProperty("telephone", is("89518719254"))))
        .andExpect(model().attribute("student", hasProperty("faculty", is(faculty))))
        .andExpect(model().attribute("student", hasProperty("cathedra", is(cathedra))))
        .andExpect(model().attribute("student", hasProperty("groupClass", is(groupClass))))
        .andExpect(view().name("studentProfile"));
  }

  @Test
  void testShowAllStudents() throws Exception {
    mockMvc.perform(get("/students"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student_list"))
        .andExpect(view().name("studentList"));
  }

  @Test
  void testInitCreationForm() throws Exception {
    mockMvc.perform(get("/students/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student"))
        .andExpect(view().name("StudentCreateOrUpdateForm"));
  }

  @Test
  void testProcessCreationFormSuccess() throws Exception {
    mockMvc.perform(post("/students/new")
        .param("fio", "Иванов Иван Иванович")
        .param("birthday", "1994-06-23")
        .param("sex", "муж")
        .param("factAddress", "-")
        .param("address", "-")
        .param("telephone", "89081355694")
        .param("faculty", "1")
        .param("cathedra", "2")
        .param("groupClass", "3")
    )
        .andExpect(status().is3xxRedirection());
  }

  //@Test
  //TODO no passes, until make validation
  void testProcessCreationFormHasErrors() throws Exception {
    mockMvc.perform(post("/students/new")
        .param("fio", "Иванов Иван Иванович")
        .param("birthday", "1994-06-23")
        .param("sex", "муж")
        .param("faculty", "1")
        .param("cathedra", "2")
        .param("groupClass", "3")
    )
        .andExpect(status().isOk())
        .andExpect(model().attributeHasErrors("student"))
        .andExpect(model().attributeHasFieldErrors("student", "factAddress"))
        .andExpect(model().attributeHasFieldErrors("student", "address"))
        .andExpect(model().attributeHasFieldErrors("student", "telephone"))
        .andExpect(view().name("StudentCreateOrUpdateForm"));
  }

  @Test
  void testInitUpdateStudentForm() throws Exception {
    mockMvc.perform(get("/students/{studentId}/edit", TEST_STUDENT_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student"))
        .andExpect(model().attribute("student", hasProperty("fio", is("Вдовенко Илья Сергеевич"))))
        .andExpect(model().attribute("student", hasProperty("birthday", is(birthday))))
        .andExpect(model().attribute("student", hasProperty("sex", is("муж"))))
        .andExpect(model().attribute("student",
            hasProperty("factAddress", is("г.Воронеж, Московский пр-кт 141"))))
        .andExpect(model().attribute("student", hasProperty("address", is("-"))))
        .andExpect(model().attribute("student", hasProperty("telephone", is("89518719254"))))
        .andExpect(model().attribute("student", hasProperty("faculty", is(faculty))))
        .andExpect(model().attribute("student", hasProperty("cathedra", is(cathedra))))
        .andExpect(model().attribute("student", hasProperty("groupClass", is(groupClass))))
        .andExpect(view().name("StudentCreateOrUpdateForm"));
  }

  @Test
  void testProcessUpdateStudentFormSuccess() throws Exception {
    mockMvc.perform(post("/students/{studentId}/edit", TEST_STUDENT_ID)
        .param("fio", "Вдовенко Илья Сергеевич")
        .param("birthday", "1994-09-26")
        .param("sex", "муж")
        .param("factAddress", "г.Воронеж, Московский пр-кт 141")
        .param("address", "-")
        .param("telephone", "89518719254")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().is3xxRedirection());
  }

  //@Test
  //TODO no passes, until make validation
  void testProcessUpdateStudentFormHasErrors() throws Exception {
    mockMvc.perform(post("/students/{studentId}/edit", TEST_STUDENT_ID)
        .param("fio", "Иванов Иван Иванович")
        .param("birthday", "1994-06-23")
        .param("sex", "муж")
        .param("faculty", "1")
        .param("cathedra", "1")
        .param("groupClass", "1")
    )
        .andExpect(status().isOk())
        .andExpect(model().attributeHasErrors("student"))
        .andExpect(model().attributeHasFieldErrors("student", "factAddress"))
        .andExpect(model().attributeHasFieldErrors("student", "address"))
        .andExpect(model().attributeHasFieldErrors("student", "telephone"))
        .andExpect(view().name("StudentCreateOrUpdateForm"));
  }
}
