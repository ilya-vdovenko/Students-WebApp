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

import javax.sql.DataSource;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.repository.StudentRepository;
import org.spring.samples.swa.repository.EmployeeRepository;

/**
 * Configuration for test class {@link CacheImplementationTests}.
 *
 * @author Ilya Vdovenko
 */

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan("org.spring.samples.swa.service")
@Import({DataSourceConfig.class, CacheConfig.class})
public class CacheTestConfig {

  @Autowired
  DataSource dataSource;
    
  @Bean("transactionManager")
  public DataSourceTransactionManager dataSourceTransactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public InstituteRepository instituteRepository() {
      return Mockito.mock(InstituteRepository.class);
  }

  @Bean
  public StudentRepository studentRepository() {
      return Mockito.mock(StudentRepository.class);
  }

  @Bean
  public EmployeeRepository employeeRepository() {
      return Mockito.mock(EmployeeRepository.class);
  }
}
