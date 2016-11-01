package co.ulock.api.data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Users", indexes = @Index(columnList = "accountId", unique = true) )
public class User extends BaseEntity {

	private String accountId;
	
	@Email
	private String email;

	@Embedded
	private EncryptData data;

	@Lob
	private String publicKey;

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
