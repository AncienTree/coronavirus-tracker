package pl.entpoint.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronovirusTrackerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronovirusTrackerTestApplication.class, args);
	}

}
