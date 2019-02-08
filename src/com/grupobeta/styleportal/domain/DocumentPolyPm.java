package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DocumentPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int documentId;
	private String filePath;
	private int tagId;
	private String fileName;
	private String fileType;
	private String urlComponent;
	
	
	@Id
	@Column(name="DocumentID")
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	
	@Column(name="FilePath")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name="TagID")
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	@Column(name="FileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="FileType")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name="URLComponent")
	public String getUrlComponent() {
		return urlComponent;
	}
	public void setUrlComponent(String urlComponent) {
		this.urlComponent = urlComponent;
	}
	
	
	
	
	
	

}
