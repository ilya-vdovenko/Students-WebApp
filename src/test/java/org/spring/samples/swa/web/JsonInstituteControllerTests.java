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
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link JsonInstituteController}
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig(locations = {"classpath:SpringConfigs/mvc-config.xml", "classpath:SpringConfigs/mvc-test-config.xml"})
public class JsonInstituteControllerTests {

  private final int TEST_FACULTY_ID = 1;
  private final int TEST_CATHEDRA_ID = 1;

  @Autowired
  private JsonInstituteController jsonInstituteController;

  @Autowired
  private InstituteService service;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(jsonInstituteController).build();

    Faculty fac1 = new Faculty();
    fac1.setId(TEST_FACULTY_ID);
    fac1.setTitle("Энергетики и систем управления");

    Faculty fac2 = new Faculty();
    fac2.setId(2);
    fac2.setTitle("Информационных технологий и компьютерной безопасности");

    Cathedra cat1 = new Cathedra();
    cat1.setId(TEST_CATHEDRA_ID);
    cat1.setTitle("Электропривода, автоматики и управления в технических системах");

    Cathedra cat2 = new Cathedra();
    cat2.setId(2);
    cat2.setTitle("Электромеханических систем и электроснабжения");

    Group_class group1 = new Group_class();
    group1.setId(1);
    group1.setTitle("АТ-121");

    Group_class group2 = new Group_class();
    group2.setId(2);
    group2.setTitle("АП-131");

    cat1.setGroup_classes(new HashSet<>(Lists.newArrayList(group1, group2)));
    fac1.setCathedras(new HashSet<>(Lists.newArrayList(cat1, cat2)));

    given(this.service.getFaculties()).willReturn(Lists.newArrayList(fac1, fac2));
    given(this.service.findFacultyById(TEST_FACULTY_ID)).willReturn(fac1);
    given(this.service.findCathedraById(TEST_CATHEDRA_ID)).willReturn(cat1);
  }

  @Test
  void testGetFacList() throws Exception {
    mockMvc.perform(get("/faculties/getFacList"))
      .andExpect(status().isOk())
      .andExpect(content().json("[{'id':1,'title':'Энергетики и систем управления'}," +
        "{'id':2,'title':'Информационных технологий и компьютерной безопасности'}]"));
  }

  @Test
  void testGetCatList() throws Exception {
    mockMvc.perform(get("/cathedras/getCatList?facultyId={id}", TEST_FACULTY_ID))
      .andExpect(status().isOk())
      .andExpect(content().json("[{'id':2,'title':'Электромеханических систем и электроснабжения'}," +
        "{'id':1,'title':'Электропривода, автоматики и управления в технических системах'}]"));
  }

  @Test
  void testGetGroupList() throws Exception {
    mockMvc.perform(get("/group_classes/getGroupList?cathedraId={id}", TEST_CATHEDRA_ID))
      .andExpect(status().isOk())
      .andExpect(content().json("[{'id':2,'title':'АП-131'},{'id':1,'title':'АТ-121'}]"));
  }
}
