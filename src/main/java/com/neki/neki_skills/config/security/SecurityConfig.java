package com.neki.neki_skills.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.neki.neki_skills.usuario.CustomUserDetailsService;
import com.neki.neki_skills.usuario.UsuarioService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> {
					req.requestMatchers(HttpMethod.POST,"/rest/auth/login").permitAll();
					req.requestMatchers("/swagger-ui/**").permitAll();
					req.requestMatchers("/swagger-ui.html").permitAll();
					req.requestMatchers("/v3/api-docs/**").permitAll();
					req.requestMatchers(HttpMethod.GET,"/api/usuario_skill/listarSkillUsuario/{id}").permitAll();
					req.requestMatchers(HttpMethod.POST,"/api/usuario/salvar").permitAll();
					//req.requestMatchers(HttpMethod.POST,"/api/skill/salvar").permitAll();
					req.requestMatchers(HttpMethod.POST,"/api/usuario_skill/salvar").permitAll();
					req.requestMatchers(HttpMethod.PUT,"/api/usuario_skill/atualizar").permitAll();
					req.requestMatchers(HttpMethod.DELETE,"/api/usuario_skill/{id}").permitAll();
					req.anyRequest().authenticated();
				}).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();
	}
	
	  @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
	        source.registerCorsConfiguration("/**", corsConfiguration);
	        return source;
	    }

}