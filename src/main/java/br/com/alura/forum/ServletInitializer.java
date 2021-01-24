package br.com.alura.forum;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** @todo
 *  *   this configuration is to use when we want to deploy the application like war
 *  *   we need to provide tomca in dependency too. I commentet this in.pom file
 * **/
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ForumApplication.class);
    }

}
