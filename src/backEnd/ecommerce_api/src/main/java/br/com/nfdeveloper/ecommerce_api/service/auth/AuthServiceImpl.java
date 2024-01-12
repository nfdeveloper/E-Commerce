package br.com.nfdeveloper.ecommerce_api.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nfdeveloper.ecommerce_api.dto.SignupRequest;
import br.com.nfdeveloper.ecommerce_api.dto.UserDTO;
import br.com.nfdeveloper.ecommerce_api.entity.User;
import br.com.nfdeveloper.ecommerce_api.enums.UserRole;
import br.com.nfdeveloper.ecommerce_api.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService{

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDTO createUser(SignupRequest signupRequest) {
		User user = new User();
		
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		
		User createUser = userRepository.save(user);
		
		UserDTO userDto = new UserDTO();
		userDto.setId(createUser.getId());
		
		return userDto;
	}
	
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	/*
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
	*/
}
