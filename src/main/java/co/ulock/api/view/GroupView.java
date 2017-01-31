package co.ulock.api.view;

import co.ulock.api.data.EncryptData;

public class GroupView {

	private String id;
	private String name;
	private EncryptData data;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EncryptData getData() {
		return data;
	}

	public void setData(EncryptData data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
