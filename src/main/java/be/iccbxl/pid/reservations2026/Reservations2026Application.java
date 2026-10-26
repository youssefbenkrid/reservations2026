package be.iccbxl.pid.reservations2026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Reservations2026Application {

	public static void main(String[] args) {
		SpringApplication.run(Reservations2026Application.class, args);
	}

}

