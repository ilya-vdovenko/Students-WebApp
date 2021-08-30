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

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * Configure class for core and view.
 *
 * @author Ilya Vdovenko
 */

@Configuration
@EnableWebMvc
@ComponentScan("org.spring.samples.swa.web")
public class MvcAppConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/")
        .setCacheControl(CacheControl.maxAge(30L, TimeUnit.DAYS).cachePublic())
        .resourceChain(true)
        .addResolver(new WebJarsResourceResolver());
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.ignoreAcceptHeader(false);
    configurer.defaultContentType(MediaType.TEXT_HTML);
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/WEB-INF/jsp/", ".jsp");
  }

  /**
   * Configurate cookie locale bean.
   *
   * @return CookieLocaleResolver.
   */
  @Bean
  public CookieLocaleResolver localeResolver() {
    CookieLocaleResolver cookie = new CookieLocaleResolver();
    cookie.setDefaultLocale(Locale.ENGLISH);
    cookie.setCookieName("StudentWebAppCookie");
    cookie.setCookieMaxAge(3600);
    return cookie;
  }

  /**
   * Configurate locale interceptor bean.
   *
   * @return localeChangeInterceptor.
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("locale");
    return lci;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
  }

  /**
   * Configurate eception resolver bean.
   *
   * @return simpleMappingExceptionResolver.
   */
  @Bean
  public HandlerExceptionResolver simpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver excepResolver = new SimpleMappingExceptionResolver();
    excepResolver.setDefaultErrorView("error");
    excepResolver.setWarnLogCategory("warn");
    return excepResolver;
  }

  @Override
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    resolvers.add(simpleMappingExceptionResolver());
  }
}
