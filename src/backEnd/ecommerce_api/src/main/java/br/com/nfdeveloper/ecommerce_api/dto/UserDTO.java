package br.com.nfdeveloper.ecommerce_api.dto;

import java.util.Objects;

import br.com.nfdeveloper.ecommerce_api.enums.UserRole;

public class UserDTO {

	private Long id;
	private String email;
	private String name;
	private UserRole userRole;
	
	public UserDTO() {
	
	}

	public UserDTO(Long id, String email, String name, UserRole userRole) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
