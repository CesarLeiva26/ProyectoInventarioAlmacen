package cibertec.edu.pe.ProyectoInventarioAlmacen.SecConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UsuarioauthDetalleService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private final UsuarioauthDetalleService usuarioauthDetalleService;

	@Bean
	public SecurityFilterChain 
		configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests()
		.antMatchers("/auth/login",
				"/auth/registrar",
				"/auth/guardarUsuario",
				"/resources/**",
				"/static/**",
				"/css/**",
				"/home/**",
				"/js/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().loginPage("/auth/login")
		.usernameParameter("txtusuario")
		.passwordParameter("txtpassword")
		.defaultSuccessUrl("/home")
		.failureUrl("/login?error=true")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/auth/login").and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied")
		.and()
		.authenticationProvider(
				authenticationProvider());
	
		return http.build();
			
	}
	
	@Bean
	public AuthenticationProvider 
	authenticationProvider() {
	DaoAuthenticationProvider authenticationProvider
		= new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(
			usuarioauthDetalleService);
	authenticationProvider.setPasswordEncoder(
			new BCryptPasswordEncoder());
	return authenticationProvider;
}
	
}
