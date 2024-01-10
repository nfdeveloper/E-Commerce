package br.com.nfdeveloper.ecommerce_api.controller;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.nfdeveloper.ecommerce_api.dto.AuthenticationRequest;
import br.com.nfdeveloper.ecommerce_api.dto.SignupRequest;
import br.com.nfdeveloper.ecommerce_api.dto.UserDTO;
import br.com.nfdeveloper.ecommerce_api.entity.User;
import br.com.nfdeveloper.ecommerce_api.repository.UserRepository;
import br.com.nfdeveloper.ecommerce_api.service.auth.AuthService;
import br.com.nfdeveloper.ecommerce_api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailService;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final AuthService authService;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailService,
			UserRepository userRepository, JwtUtil jwtUtil, AuthService authService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailService = userDetailService;
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.authService = authService;
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
		
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, "
					+ "X-Requested-With, Content-Type, Accept, X-Custom-header");
			// Adiciona ao Header
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
		}
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
		if(authService.hasUserWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDTO userDto = authService.createUser(signupRequest);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
}
