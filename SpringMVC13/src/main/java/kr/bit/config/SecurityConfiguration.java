package kr.bit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
 
	//@Autowired
	private final PrincipalOauth2UserService userDetailsService;
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
	 */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		http.authorizeHttpRequests()
			.antMatchers("/", "/member/**").permitAll()
			.antMatchers("/board/**").authenticated()
			
			.and()
			.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/board/list")
			
			.and()
			.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/")
		
		    .and()
		    .oauth2Login()
		    .defaultSuccessUrl("/board/list")
		    .userInfoEndpoint()
		    .userService(userDetailsService);
		 //http.userDetailsService(userDetailsService);
		
		 //http.oauth2Login();
		 
		return http.build();
	}
}
