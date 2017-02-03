package co.ulock.api.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Group extends BaseEntity {

	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Member> members = new ArrayList<>();

	@Embedded
	private EncryptData data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}

}
