package com.example.bootstudyhomles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.example.bootstudyhomles.controller","com.example.bootstudyhomles.service","com.example.bootstudyhomles.config"})
//@ImportResource("dispatcher-servlet.xml")
//@EnableWebMvc
@EnableAutoConfiguration
public class BootStudyHomlesApplication{


    public static void main(String[] args) {
        SpringApplication.run(BootStudyHomlesApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
