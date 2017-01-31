package co.ulock.api.view;

import co.ulock.api.data.EncryptData;
import co.ulock.api.data.Role;

public class MemberView {

	private String email;
	private Role role;
	private EncryptData data;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}
	
}
