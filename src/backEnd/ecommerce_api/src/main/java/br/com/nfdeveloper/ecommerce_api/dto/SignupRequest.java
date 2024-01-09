package br.com.nfdeveloper.ecommerce_api.dto;

import java.util.Objects;

public class SignupRequest { 
	
	private String email;
	private String password;
	private String name;
	
	public SignupRequest() {
	
	}

	public SignupRequest(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignupRequest other = (SignupRequest) obj;
		return Objects.equals(email, other.email);
	}
	
}
