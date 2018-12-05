package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Languages", schema="ADM")
public class Language extends AuditableObject {
	private static final long serialVersionUID = 1L;
	
	public final static String ENGLISH = "en";
	public final static String SPANISH = "es";
	
	private int languageId;
	private String languageCode;
	private String languageName;
	private boolean status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LanguageID", unique=true, nullable=false)
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	
	@Column(name="LanguageCode", nullable=false)
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	@Column(name="LanguageName", nullable=false)
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	@Column(name="Status", nullable=false)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return getLanguageCode() + " - " + getLanguageName();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (languageCode == null) {
			if (other.languageCode != null)
				return false;
		} else if (!languageCode.equals(other.languageCode))
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		return true;
	}
	
	

}
