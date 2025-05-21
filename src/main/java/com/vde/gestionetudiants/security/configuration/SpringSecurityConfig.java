package com.vde.gestionetudiants.security.configuration;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.vde.gestionetudiants.security.services.DymaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

   @Autowired
   private DymaUserDetailsService dymaUserDetailsService;

   private String jwtKey = "6ad8effc9f71162347b3df13e3c6714422196fce6921bd3484d4e995269c47e4";


   @Bean
   public AuthenticationManager authenticationManager(UserDetailsService userDetailsService ,PasswordEncoder passwordEncoder) throws Exception {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder);
      return new ProviderManager(authProvider);
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http.csrf(csrf -> csrf.disable())
              .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(auth -> auth
                      .requestMatchers("/etudiants/create").hasAuthority("ROLE_ADMIN")
                      .requestMatchers("/etudiants/delete/{id}").hasAuthority("ROLE_ADMIN")
                      .requestMatchers("/login").permitAll()
                      .anyRequest().authenticated())
                      .httpBasic(Customizer.withDefaults())
                      .oauth2ResourceServer(oauth2 -> oauth2
                      .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))).build();
   }

   @Bean
   public JwtAuthenticationConverter jwtAuthenticationConverter() {
      JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
      grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
      grantedAuthoritiesConverter.setAuthorityPrefix(""); // Ne pas préfixer avec "SCOPE_"

      JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
      jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
      return jwtAuthenticationConverter;
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
