package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int customerId;
	private String companyNumber;
	private String portalCode;
	private String companyName;
	
	
	@Id
	@Column(name="AddressID")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Column(name="CompanyNumber")
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	
	@Column(name="PortalCode")
	public String getPortalCode() {
		return portalCode;
	}
	public void setPortalCode(String portalCode) {
		this.portalCode = portalCode;
	}
	
	@Column(name="CompanyName")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
