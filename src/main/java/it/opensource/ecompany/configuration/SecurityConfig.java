package it.opensource.ecompany.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Bean
    @Override
    public UserDetailsManager userDetailsService() {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/login/**").permitAll()
            .antMatchers("/logout/**").permitAll()
            .antMatchers("/logout/**").permitAll()
            .antMatchers("/customers/**").permitAll()
            .antMatchers("/products/**").permitAll()
            .antMatchers("/cart/**").permitAll()
            .antMatchers("/errors/**").permitAll()
            .antMatchers("/purchaseorders/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/warehouse/**").hasRole("ADMIN")
            .antMatchers("/**").hasRole("USER")

            .and()
            .formLogin()
            .loginPage("/login/form")
            .loginProcessingUrl("/login")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()

            .and()
            .httpBasic()

            .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .permitAll()

            .and()
            .csrf()
            .disable()

            // Enable <frameset> in order to use H2 web console
            .headers()
            .frameOptions().disable(); // necessario per h2 console (?)
    }

}
