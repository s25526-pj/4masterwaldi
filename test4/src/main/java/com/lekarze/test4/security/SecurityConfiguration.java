package com.lekarze.test4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String BASE_URL = "/api/v1";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, BASE_URL + "/visits").permitAll()
                .mvcMatchers(HttpMethod.GET, BASE_URL + "/visits").permitAll()
                .mvcMatchers(HttpMethod.GET, BASE_URL + "/visits/delete/{id}").permitAll()
                .mvcMatchers(HttpMethod.GET, BASE_URL + "/visits/confirm/{id}").permitAll()
                .mvcMatchers(HttpMethod.POST, BASE_URL + "/doctors").permitAll()
                .mvcMatchers(HttpMethod.GET, BASE_URL + "/doctors").permitAll()
                .mvcMatchers(HttpMethod.DELETE, BASE_URL + "/doctors/delete/{id}").permitAll()
                .mvcMatchers(HttpMethod.POST, BASE_URL + "/patients").permitAll()
                .mvcMatchers(HttpMethod.GET, BASE_URL + "/patients").permitAll()
                .mvcMatchers(HttpMethod.DELETE, BASE_URL + "/patients/delete/{id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Doctor")
                .password(passwordEncoder().encode("TrudneHaslo"))
                .roles("DOCTOR");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
