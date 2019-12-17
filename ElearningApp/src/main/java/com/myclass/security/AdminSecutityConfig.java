package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.myclass")
public class AdminSecutityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override //cấu hình đẳng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {// Hàm này có nv ở Controller khi
		// đã lấy đc email,pass của người dùng rồi thì sẽ kiểm tra
		auth
		.userDetailsService(userDetailsService)//khi tới đây sẽ gọi tới hàm loadUserByUsername() trong class đã imple từ UserDetailsService
		.passwordEncoder(passwordEncoder());//trả về 1 đối tượng dùng để giải mã mk

	}
	
	 //cấu hình phân quyền
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		
		http.csrf().disable()
		.antMatcher("/api/admin/**")
		.authorizeRequests()
		.antMatchers("/api/admin/login")
		.permitAll()
		.anyRequest()
		.authenticated();//Bắt buộc phải có tài khoản(authentication)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(),userDetailsService));
	}
}
