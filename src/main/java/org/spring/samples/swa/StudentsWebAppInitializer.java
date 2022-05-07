/*
 * Copyright 2019-2022, Ilya Vdovenko and the Students-WebApp contributors.
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

package org.spring.samples.swa;

import javax.servlet.Filter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Create the Spring root application context. Register DispatcherServlet in the servlet context.
 *
 * @author Ilya Vdovenko
 */

public class StudentsWebAppInitializer extends AbstractDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    XmlWebApplicationContext rootAppContext = new XmlWebApplicationContext();
    rootAppContext.setConfigLocations("classpath:SpringConfigs/root-config.xml");
    return rootAppContext;
  }

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    XmlWebApplicationContext webContext = new XmlWebApplicationContext();
    webContext.setConfigLocations("classpath:SpringConfigs/mvc-config.xml");
    return webContext;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
    return new Filter[]{characterEncodingFilter};
  }
}
