package it.opensource.ecompany.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import it.opensource.ecompany.bean.CartBean;
import it.opensource.ecompany.bean.CustomerBean;
import it.opensource.ecompany.web.form.CustomerForm;

/**
 * Configura bean con scope session
 *
 * @author Paolo Bertin
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CustomerBean customers() {

        return new CustomerBean();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CustomerForm customerForm() {

        return new CustomerForm();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CartBean cartBean() {

        return new CartBean();
    }

}
