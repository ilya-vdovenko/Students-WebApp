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

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

/**
 * Configure class for backend.
 *
 * @author Ilya Vdovenko
 */

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan("org.spring.samples.swa.service")
@Import({DataSourceConfig.class,
         InitDataBase.class,
         CacheConfig.class,
         JdbcConfig.class, 
         JpaConfig.class, 
         SpringDataJpaConfig.class, 
         SharedJpaConfig.class})
public class RootAppConfig {

  /**
   * Configurate negotiation manager bean.
   *
   * @return ContentNegotiationManagerFactoryBean.
  */
  @Bean("contentNegotiationManager")
  public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
    ContentNegotiationManagerFactoryBean manager = new ContentNegotiationManagerFactoryBean();
    manager.setFavorParameter(false);
    manager.setIgnoreAcceptHeader(true);
    manager.setDefaultContentType(MediaType.APPLICATION_JSON);
    return manager;
  }

  /**
   * Configurate message source bean.
   *
   * @return ReloadableResourceBundleMessageSource.
   */
  @Bean("messageSource")
  public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
    ReloadableResourceBundleMessageSource mesSource = new ReloadableResourceBundleMessageSource();
    mesSource.setBasename("classpath:messages/messages");
    mesSource.setDefaultEncoding("UTF-8");
    return mesSource;
  }
}
