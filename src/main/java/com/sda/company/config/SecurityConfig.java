package com.sda.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("administrator")
                .password(passwordEncoder().encode("pass1"))
                .roles(SecurityRolesTypes.ADMIN.toString(), SecurityRolesTypes.USER.toString())
                .and()
                .withUser("client")
                .password(passwordEncoder().encode("pass2"))
                .roles(SecurityRolesTypes.USER.toString());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/api/v1/companies/getAll").hasRole(SecurityRolesTypes.USER.toString())
//                .antMatchers("/api/v1/companies/**").hasRole(SecurityRolesTypes.ADMIN.toString())
                .anyRequest().permitAll()
                .and()
                .httpBasic();

        http.cors().disable();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
