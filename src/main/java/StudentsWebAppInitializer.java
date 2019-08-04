import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Create the Spring root application context.
 * Register DispatcherServlet in the servlet context.
 *
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
        return new String[]{"/"};
    }
}