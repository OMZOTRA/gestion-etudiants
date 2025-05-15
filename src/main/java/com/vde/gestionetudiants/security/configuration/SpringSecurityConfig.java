package com.vde.gestionetudiants.security.configuration;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.crypto.spec.SecretKeySpec;


@Configuration
public class SpringSecurityConfig {

   private String jwtKey = "6ad8effc9f71162347b3df13e3c6714422196fce6921bd3484d4e995269c47e4";

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf(csrf -> csrf.disable())
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
               .httpBasic(Customizer.withDefaults())
               .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
               .build();
   }

   @Bean
   public UserDetailsService userDetailsService(){
      UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("User").build();
      return new InMemoryUserDetailsManager(user);
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public JwtEncoder jwtEncoder() {
      return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
   }

   @Bean
   public JwtDecoder jwtDecoder() {
      SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length,"RSA");
      return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
   }
}
