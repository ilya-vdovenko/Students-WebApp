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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Create the Spring root application context.
 * Register DispatcherServlet in the servlet context.
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
    XmlWebApplicationContext rootAppContext = new XmlWebApplicationContext();
    rootAppContext.setConfigLocations("classpath:SpringConfigs/mvc-config.xml");
    return rootAppContext;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
    return new Filter[] {characterEncodingFilter};
  }
}
