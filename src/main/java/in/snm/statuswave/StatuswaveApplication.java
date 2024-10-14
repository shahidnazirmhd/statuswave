package in.snm.statuswave;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.snm.statuswave.role.Role;
import in.snm.statuswave.user.User;
import in.snm.statuswave.user.UserRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class StatuswaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatuswaveApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            return args -> {
                var adminUser =
        User.builder()
            .firstname("Admin")
            .lastname("")
            .companyName("statuswave")
            .profileName("admin")
            .email("admin")
            .password(passwordEncoder.encode("123"))
            .role(Role.ADMIN)
            .accountLocked(false)
            .enabled(true)
            .build();
    	userRepository.save(adminUser);

            var user =
            User.builder()
                .firstname("Test")
                .lastname("Demo")
                .companyName("statuswave")
                .profileName("test")
                .email("test")
                .password(passwordEncoder.encode("123"))
                .role(Role.USER)
                .accountLocked(false)
                .enabled(true)
                .build();
                userRepository.save(user);
            };
	}

}
