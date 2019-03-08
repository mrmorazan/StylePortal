package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ColorBam extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int colorID;
	private String colorCode;
	private String colorName;
	
	@Id
	@Column(name="ColorID")
	public int getColorID() {
		return colorID;
	}
	public void setColorID(int colorID) {
		this.colorID = colorID;
	}
	
	@Column(name="ColorCode")
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
	@Column(name="ColorName")
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
	

}
