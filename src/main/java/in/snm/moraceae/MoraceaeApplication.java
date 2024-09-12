package in.snm.moraceae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MoraceaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoraceaeApplication.class, args);
	}

}
