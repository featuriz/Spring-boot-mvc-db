/**
 * 
 */
package com.featuriz.sbm.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.featuriz.sbm.security.handler.CustomAccessDeniedHandler;
import com.featuriz.sbm.security.handler.CustomAuthenticationFailureHandler;
import com.featuriz.sbm.security.handler.CustomAuthenticationSuccessHandler;
import com.featuriz.sbm.security.handler.CustomLogoutSuccessHandler;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 21-Nov-202111:34:58 pm
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
			.and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
			.and().withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/admin").hasRole("ADMIN")
	      .antMatchers("/user").hasRole("USER")
	      .antMatchers("/all").hasAnyRole("ADMIN","USER")
	      .antMatchers("/anonymous").anonymous()
	      .antMatchers("/","/login","/error").permitAll()
	      .anyRequest().authenticated()
	    .and()
	      .formLogin()
	      .loginPage("/login")
	      .loginProcessingUrl("/perform_login")
	      .successHandler(authenticationSuccessHandler())
	      .failureUrl("/login?error=true")
	      .failureHandler(authenticationFailureHandler())
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
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
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

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
