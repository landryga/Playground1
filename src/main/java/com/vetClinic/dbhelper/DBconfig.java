package com.vetClinic.dbhelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DBconfig extends WebMvcConfigurerAdapter {
	
	@Bean(name = "dataSource")
	 public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	     driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	     driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/klinika");
	     driverManagerDataSource.setUsername("root");
	     driverManagerDataSource.setPassword("Test1234");
	     return driverManagerDataSource;
	 }
}
