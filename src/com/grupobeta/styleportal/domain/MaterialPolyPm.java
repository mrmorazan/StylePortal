package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MaterialPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int rawMaterialID;
	private String partNumber;
	private String description;
	private String bodyPart;
	private String categoryName;
	private int componentCategoryID;
	private String componentName;
	
	@Id
	@Column(name="RawMaterialID")
	public int getRawMaterialID() {
		return rawMaterialID;
	}
	public void setRawMaterialID(int rawMaterialID) {
		this.rawMaterialID = rawMaterialID;
	}
	
	@Column(name="PartNumber")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	@Column(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="BodyPart")
	public String getBodyPart() {
		return bodyPart;
	}
	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}
	
	@Column(name="CategoryName")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name="ComponentCategoryID")
	public int getComponentCategoryID() {
		return componentCategoryID;
	}
	public void setComponentCategoryID(int componentCategoryID) {
		this.componentCategoryID = componentCategoryID;
	}
	
	@Column(name="ComponentName")
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	
	
	

}
