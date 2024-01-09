package br.com.nfdeveloper.ecommerce_api.controller;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import br.com.nfdeveloper.ecommerce_api.dto.AuthenticationRequest;
import br.com.nfdeveloper.ecommerce_api.entity.User;
import br.com.nfdeveloper.ecommerce_api.repository.UserRepository;
import br.com.nfdeveloper.ecommerce_api.utils.JwtUtil;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailService;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailService,
			UserRepository userRepository, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.userDetailService = userDetailService;
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws java.io.IOException, JSONException {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), 
					authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password.");
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername()); 
		
		if(optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject()
					.put("userId", optionalUser.get().getId())
					.put("role", optionalUser.get().getRole())
					.toString()
					);
			
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
		}
	}
	
	
	
}
