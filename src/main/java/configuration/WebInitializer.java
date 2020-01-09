package configuration;

import com.sun.istack.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class Webinitializer relaces web html
 * @author Polina Shcherbinina
 * @version 1.1
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Method is used for inheritance of context
     * @return null, there is no inheritance of context in this application
     */
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    /**
     * Method is used to set up where all beans are located
     * @return class where all beans are located. It marked as @Configuration
     */
    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ConfigApp.class};
    }


    /**
     * Method is used to define where servlet will map (link)
     * @return String where servlet will map
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/ad/*"};
    }
}

