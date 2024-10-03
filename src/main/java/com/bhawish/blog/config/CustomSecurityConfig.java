package com.bhawish.blog.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bhawish.blog.security.JWTAuthenticationEntryPoint;
import com.bhawish.blog.security.JwtAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	

	    @Autowired
	    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;
	    
	    public static final String[] PUBLIC_URLS= {"/api/v1/auth/**","/v3/api-docs","/v2/api-docs","/swagger-resources/**","	**","/webjars/**"};
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize-> authorize.requestMatchers(PUBLIC_URLS).permitAll().requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated());
	        
	        http.authenticationProvider(authenticationProvider());
	        http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
	        http.sessionManagement(manager  -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	       
	        
	         
	        return http.build();
	    }
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     authProvider.setUserDetailsService(customUserDetailService);
	     authProvider.setPasswordEncoder(passwordEncoder());
	     return authProvider;
	 }
	 
	 @Bean
	 public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	 }
	 

}
