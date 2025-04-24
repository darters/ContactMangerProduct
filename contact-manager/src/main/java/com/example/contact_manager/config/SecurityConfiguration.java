// package com.example.contact_manager.config;

// import static org.springframework.security.config.Customizer.withDefaults;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/contacts/*").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin(withDefaults())
//             .csrf(csrf -> csrf.disable());

//         return httpSecurity.build();
//     }
// }
