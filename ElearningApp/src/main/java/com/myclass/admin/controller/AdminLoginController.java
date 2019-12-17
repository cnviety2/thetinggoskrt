package com.myclass.admin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/admin/login")
public class AdminLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("")
	public Object login(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
			if(authentication==null)
				return new ResponseEntity<String>("Sai email hoac mk",HttpStatus.BAD_REQUEST);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = generateToke(authentication);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Sai email hoac mk", HttpStatus.BAD_REQUEST);
	}

	private String generateToke(Authentication authentication) {
		final String JWT_SECRET = "secret";
		final long JWT_EXPIRATION = 864000000L;
		Date now = new Date();
		Date expiredDate = new Date(now.getTime() + JWT_EXPIRATION);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String token = Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(expiredDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
		return token;

	}
}
