package br.com.nfdeveloper.ecommerce_api.service.auth;

import br.com.nfdeveloper.ecommerce_api.dto.SignupRequest;
import br.com.nfdeveloper.ecommerce_api.dto.UserDTO;

public interface AuthService {

	
	UserDTO createUser(SignupRequest signupRequest);
	Boolean hasUserWithEmail(String email);
}
