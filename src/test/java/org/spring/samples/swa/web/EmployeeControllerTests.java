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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.samples.swa.config.MvcTestConfig;
import org.spring.samples.swa.config.MvcAppConfig;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for {@link EmployeeController}.
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig({MvcAppConfig.class, MvcTestConfig.class})
class EmployeeControllerTests {

  private static final int TEST_EMPLOYEE_ID = 1;

  @Autowired
  private EmployeeController employeeController;

  @Autowired
  private InstituteService service;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

    Employee burk = new Employee();
    burk.setId(TEST_EMPLOYEE_ID);
    burk.setFullName("Бурковский Виктор Леонидович");
    burk.setPosition("Заведущий кафедрой");
    burk.setDegree("Доктор технических наук, профессор");

    given(this.service.findEmployeeById(TEST_EMPLOYEE_ID)).willReturn(burk);
  }

  @Test
  void testShowEmployee() throws Exception {
    mockMvc.perform(get("/employees/{employeeId}", TEST_EMPLOYEE_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("employee"))
        .andExpect(
            model().attribute("employee", hasProperty("fullName", is("Бурковский Виктор Леонидович"))))
        .andExpect(model().attribute("employee", hasProperty("position", is("Заведущий кафедрой"))))
        .andExpect(model()
            .attribute("employee", hasProperty("degree", is("Доктор технических наук, профессор"))))
        .andExpect(view().name("institute/employeeProfile"));
  }

  @Test
  void testShowAllEmployees() throws Exception {
    mockMvc.perform(get("/employees"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("employee_list"))
        .andExpect(view().name("institute/employeeList"));
  }
}
