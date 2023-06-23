package cibertec.edu.pe.ProyectoInventarioAlmacen.Configuration;

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

import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UsuarioDetalleServicio;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Securityconfig {

	@Autowired
	private final UsuarioDetalleServicio usuarioDetalleServicio;
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		
		.authorizeHttpRequests()
		.antMatchers("/auth/login",
				"/auth/registrar",
				"/auth/guardarUsuario",
				"/resources/**",
				"/static/**",
				"/css/**",
				"/js/**",
				"/usuario/usuario",
				"/home"
				)
				
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().loginPage("/auth/login")
		.usernameParameter("txtusuario")
		.passwordParameter("txtpassword")
		.defaultSuccessUrl("/home")
		.failureUrl("/auth/login?error=true")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/auth/login").and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied")
		.and()
		.authenticationProvider(
				authprovider());
		
		
		
		
		/*
		.authorizeHttpRequests()
		.antMatchers("/auth/login",
				"/auth/registar",
				"/auth/guardarUsuario",
				"/resources/**",
				"/static/**",
				"/css/**",
				"/js/**"
				
					
				)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().loginPage("/auth/registrar")
		.usernameParameter("txtusuario")
		.passwordParameter("/txtpassword")
		.defaultSuccessUrl("/frmlogin")
		.failureUrl("/auth/login?error=true")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/auth/login").and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied")
		.and()
		.authenticationProvider(authprovider());
			*/
		/*.authorizeHttpRequests()
        .antMatchers(
            "/auth/login",
            "/auth/registrar",
            "/auth/guardarUsuario",
            "/resources/**",
            "/static/**",
            "/css/**",
            "/js/**",
            "/usuario/usuario",
            "/home"
        )
        .permitAll()
        .antMatchers("/")  // Nueva regla para la página de inicio
        .authenticated()   // Requiere autenticación para la página de inicio
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .usernameParameter("txtusuario")
        .passwordParameter("txtpassword")
        .defaultSuccessUrl("/home")  // Redirige a la página de inicio después de un inicio de sesión exitoso
        .failureUrl("/auth/login?error=true")
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/home/")   // Redirige a la página de inicio después de cerrar sesión
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied")
        .and()
        .authenticationProvider(authprovider());
		
		*/
		return http.build();
	}
	

	
	@Bean
	public AuthenticationProvider authprovider() {
		DaoAuthenticationProvider daoauth=new DaoAuthenticationProvider();
		daoauth.setUserDetailsService(usuarioDetalleServicio);
		daoauth.setPasswordEncoder(new BCryptPasswordEncoder());
		return daoauth;
		
	}
	

}
