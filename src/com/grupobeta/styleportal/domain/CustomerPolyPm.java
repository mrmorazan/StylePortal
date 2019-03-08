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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyNumber == null) ? 0 : companyNumber.hashCode());
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
		CustomerPolyPm other = (CustomerPolyPm) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyNumber == null) {
			if (other.companyNumber != null)
				return false;
		} else if (!companyNumber.equals(other.companyNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return getCompanyNumber() + " - " + getCompanyName();
	}

}
