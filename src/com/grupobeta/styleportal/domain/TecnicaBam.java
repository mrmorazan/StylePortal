package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TecnicaBam extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int tecnicaID;
	private String tecnicaName;
	
	@Id
	@Column(name="TecnicaID")
	public int getTecnicaID() {
		return tecnicaID;
	}
	public void setTecnicaID(int tecnicaID) {
		this.tecnicaID = tecnicaID;
	}
	
	@Column(name="TecnicaName")
	public String getTecnicaName() {
		return tecnicaName;
	}
	public void setTecnicaName(String tecnicaName) {
		this.tecnicaName = tecnicaName;
	}
	
	

}
