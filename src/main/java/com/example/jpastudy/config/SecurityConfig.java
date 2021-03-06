package com.example.jpastudy.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : DaEunKim
 * @version : 2022/03/28
 * @fileName : com.example.jpastudy.config
 * @description :
 */
@Configuration
@EnableWebSecurity // spring security 설정을 직접 하기 위해
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.mvcMatchers("/", ".login", "/sign-up", "/check-email", "/check-email-token"
				, "/email-login", "check-email-login", "/login-link").permitAll()
				.mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
				.anyRequest().authenticated();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}
