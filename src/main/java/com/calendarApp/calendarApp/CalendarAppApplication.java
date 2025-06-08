package com.calendarApp.calendarApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.calendarApp.calendarApp.repository")
@EntityScan(basePackages = "com.calendarApp.calendarApp.entity")
public class CalendarAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarAppApplication.class, args);
	}

}
