/**
 * 
 */
package com.featuriz.sbm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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
                auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
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
	      .formLogin()
	      .loginPage("/login")
	      .loginProcessingUrl("/perform_login")
	      .usernameParameter("user_name")
          .passwordParameter("password")
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

}
