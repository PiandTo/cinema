package edu.school21.cinema.config;

import edu.school21.cinema.filters.LogInterceptor;
import edu.school21.cinema.filters.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@ComponentScan("edu.school21.cinema")
@EnableWebMvc
public class WebApplicationContextConfig implements WebMvcConfigurer {
//    private final WebApplicationContext webApplicationContext;
//
//    @Autowired
//    public WebApplicationContextConfig(WebApplicationContext webApplicationContext) {
//        this.webApplicationContext = webApplicationContext;
//    }

    @Bean
    public ViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver("", ".ftl");
        resolver.setContentType("text/html; charset=utf-8");
        resolver.setCache(true);
        return resolver;
    }

    @Bean
    public UserInterceptor userInterceptor() { return new UserInterceptor(); }

    @Bean
    public LogInterceptor logInterceptor() { return new LogInterceptor(); }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/html/");
        return freeMarkerConfigurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**", "/static/**", "/avatars/**", "/images/**").addResourceLocations("/webjars/", "/static/", "/avatars/", "/images/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor()).addPathPatterns("/profile", "/films", "/admin/**", "/signIn", "/signUp");
        registry.addInterceptor(userInterceptor()).addPathPatterns("/admin/**");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
