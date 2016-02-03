package co.ulock.api.data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
//FIXME 
//@Table(indexes = { @Index(columnList = "accountId", unique = true) })
public class Settings extends BaseEntity {

	private String accountId;

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

}
