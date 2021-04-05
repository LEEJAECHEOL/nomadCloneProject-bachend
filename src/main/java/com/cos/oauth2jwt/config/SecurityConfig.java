package com.cos.oauth2jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.oauth2jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.oauth2jwt.config.jwt.JwtAuthorizationFilter;
import com.cos.oauth2jwt.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsConfig corsConfig;
	
	private final UserRepository userRepository;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Bearer Auth(STATELESS)를 사용하겠다고 시큐리티에 알려줘야함.
		http
			.addFilter(corsConfig.corsFilter()) // @CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // STATELESS: session을 사용하지 않겠다는 의미
		.and()
			.formLogin().disable()
			.httpBasic().disable()
			.addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
			.authorizeRequests()
			.antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();
	}
	

}
