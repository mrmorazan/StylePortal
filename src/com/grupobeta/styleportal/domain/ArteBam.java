package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArteBam extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private long arteId;
	private String arte;
	
	@Id
	@Column(name="ID_ARTE")
	public long getArteId() {
		return arteId;
	}
	public void setArteId(long arteId) {
		this.arteId = arteId;
	}
	
	@Column(name="GRAFICO")
	public String getArte() {
		return arte;
	}
	public void setArte(String arte) {
		this.arte = arte;
	}
	
	

}
