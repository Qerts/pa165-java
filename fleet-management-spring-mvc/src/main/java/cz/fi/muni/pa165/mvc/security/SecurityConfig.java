package cz.fi.muni.pa165.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;


/**
 * @author Jozef Krcho
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"cz.fi.muni.pa165.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMINISTRATOR')")
                .antMatchers("/technician/**").access("hasRole('ADMINISTRATOR') or hasRole('SERVICEMAN')")
                //.anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                    .usernameParameter("inputEmail").passwordParameter("inputPassword")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
                   /* .permitAll()
                    .and()
                .logout()
                    .permitAll(); */
    }

    @Inject
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}

