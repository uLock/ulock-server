package co.ulock.api.data;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Embeddable
@JsonNaming(LowerCaseWithUnderscoresStrategy.class)
public class EncryptData {

	// {cipher_text: cipherText, salt: forge.util.encode64(salt), iv:
	// forge.util.encode64(iv)};

	@Lob
	private String cipherText;
	private String salt;
	private String iv;

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

}
