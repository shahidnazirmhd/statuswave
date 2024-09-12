package in.snm.moraceae;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.snm.moraceae.role.Role;
import in.snm.moraceae.user.User;
import in.snm.moraceae.user.UserRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MoraceaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoraceaeApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            return args -> {
                var user =
        User.builder()
            .firstname("Shahid")
            .lastname("Z")
            .email("admin")
            .password(passwordEncoder.encode("123"))
            .role(Role.ADMIN)
            .build();
    	userRepository.save(user);
            };
	}

}
