package com.featuriz.sbm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.featuriz.sbm.security.auth.SimpleAuthenticationFilter;
import com.featuriz.sbm.security.handler.CustomAccessDeniedHandler;
import com.featuriz.sbm.security.handler.CustomAuthenticationFailureHandler;
import com.featuriz.sbm.security.handler.CustomAuthenticationSuccessHandler;
import com.featuriz.sbm.security.handler.CustomLogoutSuccessHandler;
import com.featuriz.sbm.service.MyUserDetailsService;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 21-Nov-202111:34:58 pm
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/","/login","/registration","/error").permitAll()
	      .antMatchers("/anonymous").anonymous()
	      .antMatchers("/admin/**").hasRole("ADMIN")
	      .antMatchers("/user").hasRole("USER")
	      .antMatchers("/all").hasAnyRole("ADMIN","USER")
	      .anyRequest().authenticated()
	    .and()
	      .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	      .formLogin()
	      .loginPage("/login")
	    .and()
	      .logout()
	      .logoutUrl("/perform_logout")
	      .deleteCookies("JSESSIONID")
	      .logoutSuccessHandler(logoutSuccessHandler())
	    .and()
	        .exceptionHandling().accessDeniedPage("/accessDenied")
	        .accessDeniedHandler(accessDeniedHandler());
	      ;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		// Custom redirect. Check inside.
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

	/*
	 * This will work for only "/perform_login", "POST" :: Check Inside constructor
	 * Success and Failure can be handled inside, but it cost some more time. Better its here.
	 */
	@Bean
	public SimpleAuthenticationFilter authenticationFilter() throws Exception {
		SimpleAuthenticationFilter authenticationFilter = new SimpleAuthenticationFilter();
		authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationFilter;
	}

	/*
	 * This will pass AuthenticationManager to SimpleAuthenticationFilter
	 * https://www.codejava.net/frameworks/spring-boot/spring-security-before-authentication-filter-examples
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
