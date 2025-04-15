package ma.abid.eductionPlatform.security;

import lombok.RequiredArgsConstructor;
import ma.abid.eductionPlatform.services.user.UserDetailsServiceImpl;
import ma.abid.eductionPlatform.services.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(auth->auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/***", "/swagger-ui.html").permitAll()
//                .requestMatchers(HttpMethod.GET, "//api/v1/users/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll().anyRequest().authenticated());
        httpSecurity.csrf(csrf-> csrf.ignoringRequestMatchers("/api/v1/**"));
        httpSecurity.formLogin().permitAll();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

//        httpSecurity.authorizeHttpRequests().requestMatchers("/swagger-ui/**").authenticated();
//        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//        httpSecurity.formLogin().permitAll();
//        httpSecurity.cors(cors-> cors.disable());
//        httpSecurity.userDetailsService(userDetailsService);
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
