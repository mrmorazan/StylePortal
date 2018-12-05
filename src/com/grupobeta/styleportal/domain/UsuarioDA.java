package com.grupobeta.styleportal.domain;

import javax.persistence.Transient;

public class UsuarioDA extends DomainObject {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@Transient
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
