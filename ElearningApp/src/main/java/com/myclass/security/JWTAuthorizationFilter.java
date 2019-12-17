package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final String JWT_SECRET="secret";
	private UserDetailsService _userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
		super(authenticationManager);
		this._userDetailsService=userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		//Lấy token từ header do ng dùng gửi lên
		String tokenBearer=request.getHeader("Authorization");
		//Kiểm tra có phải Bearer ko
		if(tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
			//Xóa tiền tố Bearer
			String token=tokenBearer.replace("Bearer ", "");
			//Giải mã token lấy thông tin(email)
			String email=Jwts.parser()
					.setSigningKey(JWT_SECRET)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			//Lấy thông tin từ database
			UserDetails userDetails=_userDetailsService.loadUserByUsername(email);
			Authentication authentication= new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			//Gán thông tin vào Security context
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

}
