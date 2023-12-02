package com.fatma.users.security;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*@Autowired
AuthenticationManager authMgr;
@Bean
public AuthenticationManager authManager(HttpSecurity http, 
BCryptPasswordEncoder bCryptPasswordEncoder, 
UserDetailsService userDetailsService) 
 throws Exception {
 return http.getSharedObject(AuthenticationManagerBuilder.class)
 .userDetailsService(userDetailsService)
 .passwordEncoder(bCryptPasswordEncoder)
 .and()
 .build();
}
@Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
 http.csrf().disable()
.sessionManagement().
sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
.cors().configurationSource(new CorsConfigurationSource() {
	 @Override
	 public CorsConfiguration getCorsConfiguration(HttpServletRequest
	request) {
	 CorsConfiguration config = new CorsConfiguration();

	config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	 config.setAllowedMethods(Collections.singletonList("*"));
	 config.setAllowCredentials(true);
	 config.setAllowedHeaders(Collections.singletonList("*"));
	 config.setExposedHeaders(Arrays.asList("Authorization"));
	 config.setMaxAge(3600L);
	 return config;
	 }
	 }).and()
 .authorizeHttpRequests()
.requestMatchers("/login").permitAll()
.requestMatchers("/all").hasAuthority("ADMIN")
 .anyRequest().authenticated().and()
 .addFilterBefore(new
		 JWTAuthenticationFilter(authMgr),UsernamePasswordAuthenticationFilter.class)
 .addFilterBefore(new
		 JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		 ;

return http.build();
}
}*/
    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public AuthenticationManager authenticationManager
            (HttpSecurity httpSecurity,
             BCryptPasswordEncoder bCryptPasswordEncoder,
             UserDetailsService userDetailsService
            ) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowCredentials(true);
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));
                        cors.setMaxAge(3600L);
                        return cors;
                    }
                }).and()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                //je veut que la requete /all soit accessible par tous les utilisateurs
                .requestMatchers("/all").permitAll()
                .requestMatchers("/add").permitAll()
                .requestMatchers("/addRole").permitAll()
                .requestMatchers("/findUserById").permitAll()
                .requestMatchers("/allRoles").permitAll()
                .requestMatchers("/deleteUserById").permitAll()
                .requestMatchers("/findRoleById").permitAll()
                .requestMatchers("/removeRoleFromUer").permitAll()
                .requestMatchers("/activateUser/{username}/{code}").permitAll()

                



                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
/*import java.util.Arrays;


import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    AuthenticationManager authenticationManager;

	@Autowired
 	AuthenticationManager authMgr;
	

	@Autowired
 	UserDetailsService userDetailsService;
	
	@Bean

	public AuthenticationManager authManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, 
			UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
    
	 @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		  http.csrf().disable();
		  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  http.authorizeRequests().requestMatchers("/login").permitAll();  
		  http.authorizeRequests().requestMatchers("/register/**").permitAll();
		  http.authorizeRequests().requestMatchers("/all").hasAuthority("ADMIN");
		  http.authorizeRequests().requestMatchers("/api/removeRoleFromUser").permitAll();
		  http.authorizeRequests().requestMatchers("/api/allRoles").permitAll();
		  http.authorizeRequests().requestMatchers("/api/addRole").permitAll();
		  http.authorizeRequests().anyRequest().authenticated();
		  http.addFilter(new  JWTAuthenticationFilter (authMgr)) ;
		  http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		  return http.build();
		  
     }

  /*  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowCredentials(true);
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));
                        cors.setMaxAge(3600L);
                        return cors;
                    }
                }).and()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                //je veut que la requete /all soit accessible par tous les utilisateurs
                .requestMatchers("/all").permitAll()
                .requestMatchers("/add").permitAll()
                .requestMatchers("/addRole").permitAll()
                .requestMatchers("/findUserById").permitAll()
                .requestMatchers("/allRoles").permitAll()
                .requestMatchers("/deleteUserById").permitAll()
                .requestMatchers("/findRoleById").permitAll()
                .requestMatchers("/removeRoleFromUer").permitAll()



                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }*/
   
