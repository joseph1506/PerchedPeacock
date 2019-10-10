package com.parking.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.parking.maintenance.repositories")
public class ParkingMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingMaintenanceApplication.class, args);
	}

}
