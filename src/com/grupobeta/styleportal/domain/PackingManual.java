package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="PackingManual", schema="REG")
public class PackingManual extends AuditableObject {
	private static final long serialVersionUID = 1L;
	
	private long packingManualId;
	private int polyCustomerId;
	private String polyCustomerCode;
	private String polyCustomerName;
	private Integer seasonId;
	private String seasonName;
	private Integer styleId;
	private String styleNumber;
	private String fileName;
	private String urlFile;
	private boolean status;
	private CustomerPolyPm customerPolyPm;
	private SeasonPolyPm seasonPolyPm;
	
	public PackingManual() {
		this.status=true;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PackingManualID", nullable=false, unique=true)
	public long getPackingManualId() {
		return packingManualId;
	}
	public void setPackingManualId(long packingManualId) {
		this.packingManualId = packingManualId;
	}
	
	@Column(name="PolyCustomerID")
	public int getPolyCustomerId() {
		return polyCustomerId;
	}
	public void setPolyCustomerId(int polyCustomerId) {
		this.polyCustomerId = polyCustomerId;
	}
	
	@Column(name="PolyCustomerCode")
	public String getPolyCustomerCode() {
		return polyCustomerCode;
	}
	public void setPolyCustomerCode(String polyCustomerCode) {
		this.polyCustomerCode = polyCustomerCode;
	}
	
	@Column(name="PolyCustomerName")
	public String getPolyCustomerName() {
		return polyCustomerName;
	}
	public void setPolyCustomerName(String polyCustomerName) {
		this.polyCustomerName = polyCustomerName;
	}
	
	@Column(name="SeasonID")
	public Integer getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}
	
	@Column(name="SeasonName")
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	
	@Column(name="StyleID")
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	
	@Column(name="StyleNumber")
	public String getStyleNumber() {
		return styleNumber;
	}
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	
	@Column(name="FileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="URLFile")
	public String getUrlFile() {
		return urlFile;
	}
	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}
	
	@Column(name="Status")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	@Transient
	public CustomerPolyPm getCustomerPolyPm() {
		return customerPolyPm;
	}
	public void setCustomerPolyPm(CustomerPolyPm customerPolyPm) {
		this.customerPolyPm = customerPolyPm;
	}
	
	@Transient
	public SeasonPolyPm getSeasonPolyPm() {
		return seasonPolyPm;
	}
	public void setSeasonPolyPm(SeasonPolyPm seasonPolyPm) {
		this.seasonPolyPm = seasonPolyPm;
	}
	
	@Override
	public String toString() {
		return getFileName();
	}

}
