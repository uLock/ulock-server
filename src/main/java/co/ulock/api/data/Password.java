package co.ulock.api.data;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Password extends BaseEntity {

	@Embedded
	private EncryptData data;

	private String accountId;

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}

	@JsonIgnore
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
