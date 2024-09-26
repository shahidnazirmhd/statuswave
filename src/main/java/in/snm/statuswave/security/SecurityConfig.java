package in.snm.statuswave.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import in.snm.statuswave.security.custom.CustomAuthenticatioFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/css/*",
            "/js/*",
            "/i/**",
            // "/error",
            "/",
            "fragments/auth/*",
            "/register",
            "/verify"
        };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        .authorizeHttpRequests((requests) -> requests
				.requestMatchers(WHITE_LIST_URL).permitAll()
				.anyRequest()
                .authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
                .defaultSuccessUrl("/land", true)
                .failureHandler(customAuthenticationFailureHandler())
				.permitAll()
            )
    .sessionManagement((sessions) -> sessions
						.sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
                              //  .expiredUrl("/expired")
						)
                        // .invalidSessionUrl("/invalid")
                         
				)
    .logout((logout) -> logout
                .deleteCookies("JSESSIONID")
                .permitAll());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticatioFailureHandler();
    }
}
