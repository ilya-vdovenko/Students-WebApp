import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Create the Spring root application context.
 * Register DispatcherServlet in the servlet context.
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
