package ca.bytetube.communityApp.config.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Turn on Mvc and automatically inject into spring container, WebMvcConfigurerAdapter: configure view resolver
 * The class can get all beans from ApplicationContext file when implements the interface(ApplicationContextAware).
 */
@Configuration
// Equal to <mvc:annotation-driven/>
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {
    // Spring container
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Static resource configure
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {};

    /**
     * Config default servlet handler
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Create viewResolver
     */
    @Bean(name = "viewResolver")
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // Set Spring container
        viewResolver.setApplicationContext(this.applicationContext);
        // Disable cache
        viewResolver.setCache(false);
        // Set parse prefix
        viewResolver.setPrefix("/WEB-INF/html/");
        // Set parse Suffix
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * File upload resolver
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        // 1024 * 1024 * 20 = 20971520
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);

        return multipartResolver;
    }



}
