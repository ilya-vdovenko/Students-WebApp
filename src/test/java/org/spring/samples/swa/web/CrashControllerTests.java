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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Test class for {@link CrashController}
 *
 * @author Ilya Vdovenko
 */

@SpringJUnitWebConfig(locations = {"classpath:SpringConfigs/mvc-config.xml",
    "classpath:SpringConfigs/mvc-test-config.xml"})
class CrashControllerTests {

  @Autowired
  private CrashController crashController;

  @Autowired
  private SimpleMappingExceptionResolver simpleMappingExceptionResolver;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(crashController)
        .setHandlerExceptionResolvers(simpleMappingExceptionResolver)
        .build();
  }

  @Test
  void testTriggerException() throws Exception {
    mockMvc.perform(get("/oups"))
        .andExpect(view().name("error"))
        .andExpect(model().attributeExists("exception"))
        .andExpect(forwardedUrl("error"))
        .andExpect(status().isOk());
  }

}
