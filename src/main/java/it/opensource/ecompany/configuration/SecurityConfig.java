package it.opensource.ecompany.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String GROUP_AUTHORITIES_BY_USERNAME_QUERY = " " +
        "select g.id, g.group_name, ga.authority " +
        "from groups g, group_members gm, group_authorities ga " +
        "where gm.username = ? " + "and g.id = ga.group_id and g.id = gm.group_id";

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .groupAuthoritiesByUsername(GROUP_AUTHORITIES_BY_USERNAME_QUERY)
            .passwordEncoder(passwordEncoder())
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        return passwordEncoder;
    }

    @Bean
    @Override
    public UserDetailsManager userDetailsService() {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);

        return manager;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
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

            .antMatchers("/api/login/**").permitAll()
            .antMatchers("/api/logout/**").permitAll()
            .antMatchers("/api/logout/**").permitAll()
            .antMatchers("/api/customers/**").permitAll()
            .antMatchers("/api/categories/**").permitAll()
            .antMatchers("/api/products/**").permitAll()
            .antMatchers("/api/cart/**").permitAll()
            .antMatchers("/api/errors/**").permitAll()
            .antMatchers("/api/purchaseorders/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/warehouse/**").hasRole("ADMIN")

            //.antMatchers("/**").hasRole("USER")

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
            .frameOptions().disable()  // necessario per h2 console (?)
        ;
    }
}
