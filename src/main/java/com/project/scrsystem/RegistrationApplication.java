package com.project.scrsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.mappers.ModelMapper;

@EnableScheduling
@SpringBootApplication
public class RegistrationApplication {
    public static void main(String[] args) {

        SpringApplication.run(RegistrationApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
