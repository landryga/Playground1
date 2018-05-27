package com.vetClinic.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 DataSource dataSource;
	 
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	
	   auth.jdbcAuthentication().dataSource(dataSource)
	  .usersByUsernameQuery(
	   "select u.email as username, h.haslo as password, 1 from uzytkownik u, uzytkownik_haslo h where u.id = h.id_uzytkownika and u.email=?")
	  .authoritiesByUsernameQuery(
	   "select u.email as username, r.nazwa as role from uzytkownik u left join uzytkownik_rola ru on u.id = ru.uzytkownik_id "
	   + "left join rola r on ru.rola_id = r.id where  u.email=?");
	 } 
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/login").permitAll()
		 	.antMatchers("/", "/*webservice*/**", "/admin").access("hasRole('ADMIN')")
			.antMatchers("/", "/*webservice*/**").access("hasRole('USER')").and()
			.formLogin();
		 
		 
		 http.csrf().disable();
	 }
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception {
	     web.ignoring().antMatchers("/external");
	 }

}
