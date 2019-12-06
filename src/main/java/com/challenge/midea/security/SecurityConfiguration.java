package com.challenge.midea.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.challenge.midea.filters.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

//	@Autowired
//	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Set your configurations on the auth object
		
		//for JDBC + JPA Authentication using UserDetailsService (preferred)
		auth.userDetailsService(userDetailsService);
	}
	
	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Set your configurations on the auth object
		
		//for LDAP Authentication
		auth.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource()
			.url("ldap://localhost:8389/dc=springframework,dc=org")
			.and()
			.passwordCompare()
			.passwordEncoder(new LdapShaPasswordEncoder())
			.passwordAttribute("userPassword");
	}
	*/
	
	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Set your configurations on the auth object
		
		//for JDBC Authentication
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username,password,enabled "
					+ "from users "
					+ "where username = ?")
			.authoritiesByUsernameQuery("select username,authority "
					+ "from authorities "
					+ "where username = ?");
		
		//the above methods are useful with custom queries	
	}
	*/

	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Set your configurations on the auth object
		
		//for inMemory Authentication
		auth.inMemoryAuthentication()
			.withUser("muncha")
			.password("muncha")
			.roles("USER")
			.and()
			.withUser("mano")
			.password("mano")
			.roles("ADMIN");
		
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/**").permitAll()
		.and().formLogin();
	}
	*/
	
	/*
	// Authorization Implementation - Only for LDAP
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.formLogin();
	}
	*/
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
