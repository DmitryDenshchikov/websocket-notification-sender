package denshchikov.dmitry.websocketnotificationsender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/ws/notifications").hasAuthority("SCOPE_notifications.read")
                        .requestMatchers(POST, "/notifications/**").permitAll()
                )
                .oauth2ResourceServer(configurer -> configurer
                        .jwt(withDefaults())
                )
                .build();
    }

}