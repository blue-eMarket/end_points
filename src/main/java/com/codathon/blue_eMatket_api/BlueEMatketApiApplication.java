package com.codathon.blue_eMatket_api;

import com.codathon.blue_eMatket_api.config.SpringAuditorAware;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(info =@Info(title = "Blue_eMarket APIs"))
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BlueEMatketApiApplication {
	@Bean
	public AuditorAware<String> auditorAware() {

		return new SpringAuditorAware();
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(BlueEMatketApiApplication.class, args);
	}

}
