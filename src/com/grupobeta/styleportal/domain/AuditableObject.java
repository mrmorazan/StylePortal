package com.grupobeta.styleportal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AuditableObject extends DomainObject {
	private static final long serialVersionUID = 1L;
	private String userCreate;
	private Date dateCreate;
	private String userChange;
	private Date dateChange;

	@Column(name="UserCreate", length = 50)
	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateCreate")
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Column(name="UserChange", length = 50)
	public String getUserChange() {
		return userChange;
	}

	public void setUserChange(String userChange) {
		this.userChange = userChange;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateChange")
	public Date getDateChange() {
		return dateChange;
	}

	public void setDateChange(Date dateChange) {
		this.dateChange = dateChange;
	}



}
