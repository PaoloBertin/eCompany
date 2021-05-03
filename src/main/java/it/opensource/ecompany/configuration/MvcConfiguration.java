package it.opensource.ecompany.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.util.Locale;

// @EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

//    @Bean
//    StandardServletMultipartResolver multipartResolver() {

//        return new StandardServletMultipartResolver();
//    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

//        configurer.enable();
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/assets/**")
//                .addResourceLocations("/resources/bootstrap/");
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//        registry.addInterceptor(themeChangeInterceptor());
//    }

//    @Bean
//    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
//        return (factory) -> factory.setRegisterDefaultServlet(true);
//    }

//    @Bean
//    public HandlerInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");

//        return localeChangeInterceptor;
//    }

//    @Bean
//    public LocaleResolver localeResolver() {
        // SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        // sessionLocaleResolver.setDefaultLocale(Locale.ITALY);
        // return sessionLocaleResolver;

//        return new CookieLocaleResolver();
//    }





    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }


//    @Bean
//    ThemeChangeInterceptor themeChangeInterceptor() {
//        return new ThemeChangeInterceptor();
//    }

//    @Bean
//    ResourceBundleThemeSource themeSource() {
//        return new ResourceBundleThemeSource();
//    }

//    @Bean
//    CookieThemeResolver themeResolver() {
//        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
//        cookieThemeResolver.setDefaultThemeName("standard");
//        cookieThemeResolver.setCookieMaxAge(3600);
//        cookieThemeResolver.setCookieName("theme");

//        return cookieThemeResolver;
//    }

}
