package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Dictionary", schema="ADM")
public class Dictionary extends AuditableObject {
	private static final long serialVersionUID = 1L;
	
	private int dictionaryId;
	private Language language;
	private String messageSource;
	private String originalText;
	private String translatedText;
	private boolean isGeneral;
	private boolean status;
	
	public Dictionary() {
		this.isGeneral=false;
		this.status=true;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DictionaryID", nullable=false, unique=true)
	public int getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(int dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LanguageID")
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name="MessageSource")
	public String getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}

	@Column(name="OriginalText")
	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	@Column(name="TranslatedText")
	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	@Column(name="IsGeneral")
	public boolean isGeneral() {
		return isGeneral;
	}

	public void setGeneral(boolean isGeneral) {
		this.isGeneral = isGeneral;
	}

	@Column(name="Status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}
