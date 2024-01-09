package br.com.nfdeveloper.ecommerce_api.dto;

import java.util.Objects;

public class AuthenticationRequest {

	private String username;
	private String password;
	
	public AuthenticationRequest() {
	
	}

	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationRequest other = (AuthenticationRequest) obj;
		return Objects.equals(username, other.username);
	}

	
}
