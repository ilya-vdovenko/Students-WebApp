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

package org.spring.samples.swa.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * Configure class for data base.
 *
 * @author Ilya Vdovenko
 */

@Configuration
public class InitDataBase {

  @Autowired
  Environment env;

  @Autowired
  DataSource dataSource;

  /**
   * Configurate initialization of data base.
   */
  @PostConstruct
  public void init() {
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.initLocation")));
    databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.dataLocation")));
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
  }
}
