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

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
* Shared configuration for jpa, spring-data-jpa profile.
*
* @author Ilya Vdovenko
*/

@Configuration
@Profile({"jpa", "spring-data-jpa"})
@PropertySource("classpath:spring/data-access.properties")
public class SharedJpaConfig {

  @Autowired
  private Environment env;

  /**
   * Congigurate entity manager bean. 
   *
   * @return entityManagerFactory.
   */
  @Bean
  public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setJpaVendorAdapter(jpaVendorAdapter());
    Properties jpaProp = new Properties();
    jpaProp.put("hibernate.generate_statistics", env.getProperty("jpa.statistics", Boolean.class));
    jpaProp.put("hibernate.enable_lazy_load_no_trans", true);
    em.setJpaProperties(jpaProp);
    em.setPackagesToScan("org.spring.samples");
    em.afterPropertiesSet();
    return em.getObject();
  }

  /**
   * Congigurate Hibernate adapter bean.
   *
   * @return jpaVendorAdapter.
   */
  @Bean
  public HibernateJpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(env.getProperty("jpa.database", Database.class));
    vendorAdapter.setShowSql(env.getProperty("jpa.showSql", Boolean.class));
    return vendorAdapter;
  }

  /**
   * Congigurate transaction manager bean. 
   *
   * @return jpaTransactionManager.
   */
  @Bean("transactionManager")
  public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager manager = new JpaTransactionManager();
    manager.setEntityManagerFactory(entityManagerFactory);
    return manager;
  }
    
  @Bean
  public PersistenceExceptionTranslationPostProcessor postProcessor() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
}
