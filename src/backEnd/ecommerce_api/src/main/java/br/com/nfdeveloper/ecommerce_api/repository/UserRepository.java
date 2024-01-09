package br.com.nfdeveloper.ecommerce_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nfdeveloper.ecommerce_api.entity.User;
import br.com.nfdeveloper.ecommerce_api.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);
	
	User findByRole(UserRole userRole);
}
