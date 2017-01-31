package co.ulock.api.data;

import javax.persistence.Embedded;

public class Member extends BaseEntity {

	private User user;
	private Role role;

	@Embedded
	private EncryptData data;

	public Member() {
	}

	public Member(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
