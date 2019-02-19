package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name="POLYHEADERSTYLE",
			query= "SELECT s.StyleID " + ",s.StyleNumber " + ",s1.SeasonName " + ",ss.SilhouetteName "
					+ ",s.StyleName " + ",sn.StatusID " + ",sn.StatusName " + ",s.CustomerID " + ",d.DivisionName "
					+ ",sc.StyleCategoryName " + ",s.Comments3 Pattern " + ",sc.StyleSubcategoryName " + ",s.Memo YarnType "
					+ ",sc1.CollectionName " + ",s.Memo2 Fiber " + ",ddv.DropDownValue GearLine "
					+ ",ISNULL(REPLACE('file://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d1.FilePath , '\\' , '/'),0) AS 'URLStyleImage' "
					+ ",ISNULL(REPLACE('smb://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d1.FilePath , '\\' , '/'),0) AS 'URLStyleImage2' "
					+ "FROM Styles s " + "LEFT JOIN Seasons s1 " + "ON s.SeasonID = s1.SeasonID "
					+ "LEFT JOIN StatusNames sn " + "ON s.StatusID = sn.StatusID " + "LEFT JOIN StyleSilhouettes ss "
					+ "ON s.SilhouetteID = ss.SilhouetteID " + "LEFT JOIN Divisions d " + "ON s.DivisionID = d.DivisionID "
					+ "LEFT JOIN StyleCategories sc " + "ON s.StyleCategoryID = sc.StyleCategoryID "
					+ "LEFT JOIN StyleCollections sc1 " + "ON s.CollectionID = sc1.CollectionID "
					+ "LEFT JOIN DropDownValues ddv " + "ON s.GearLineID = ddv.DropDownValueID " + "LEFT JOIN Addresses a "
					+ "ON s.CustomerID = a.AddressID " + "LEFT JOIN Documents d1 "
					+ "ON d1.FolderPath = 'Styles\' + LTRIM(RTRIM(s.StyleNumber)) + LTRIM(RTRIM(a.CompanyNumber)) + LTRIM(RTRIM(s1.SeasonName)) "
					+ "AND d1.[FileName] = LTRIM(RTRIM(s.StyleNumber)) " + "WHERE s.CustomerID = :customerId "
					+ "AND s.StyleNumber = :styleNumber "
					+ "AND s1.SeasonName = :seasonName "
	, resultClass = StylePolyPm.class),

	@NamedNativeQuery(name="POLYHEADERSTYLE2",
	query= "SELECT s.StyleID " + ",s.StyleNumber " + ",s1.SeasonName " + ",ss.SilhouetteName "
			+ ",s.StyleName " + ",sn.StatusID " + ",sn.StatusName " + ",s.CustomerID " + ",d.DivisionName "
			+ ",sc.StyleCategoryName " + ",s.Comments3 Pattern " + ",sc.StyleSubcategoryName " + ",s.Memo YarnType "
			+ ",sc1.CollectionName " + ",s.Memo2 Fiber " + ",ddv.DropDownValue GearLine "
			+ ",ISNULL(REPLACE('file://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d1.FilePath , '\\' , '/'),0) AS 'URLStyleImage' "
			+ ",ISNULL(REPLACE('smb://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d1.FilePath , '\\' , '/'),0) AS 'URLStyleImage2' "
			+ "FROM Styles s " + "LEFT JOIN Seasons s1 " + "ON s.SeasonID = s1.SeasonID "
			+ "LEFT JOIN StatusNames sn " + "ON s.StatusID = sn.StatusID " + "LEFT JOIN StyleSilhouettes ss "
			+ "ON s.SilhouetteID = ss.SilhouetteID " + "LEFT JOIN Divisions d " + "ON s.DivisionID = d.DivisionID "
			+ "LEFT JOIN StyleCategories sc " + "ON s.StyleCategoryID = sc.StyleCategoryID "
			+ "LEFT JOIN StyleCollections sc1 " + "ON s.CollectionID = sc1.CollectionID "
			+ "LEFT JOIN DropDownValues ddv " + "ON s.GearLineID = ddv.DropDownValueID " + "LEFT JOIN Addresses a "
			+ "ON s.CustomerID = a.AddressID " + "LEFT JOIN Documents d1 "
			+ "ON d1.FolderPath = 'Styles\' + LTRIM(RTRIM(s.StyleNumber)) + LTRIM(RTRIM(a.CompanyNumber)) "
			+ "AND d1.[FileName] = LTRIM(RTRIM(s.StyleNumber)) " + "WHERE s.CustomerID = :customerId "
			+ "AND s.StyleNumber = :styleNumber "
			
, resultClass = StylePolyPm.class)
})
public class StylePolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int styleId;
	private String styleNumber;
	private String seasonName;
	private String silhouetteName;
	private String styleName;
	private int statusId;
	private String statusName;
	private int customerId;
	private String divisionName;
	private String styleCategoryName;
	private String pattern;
	private String styleSubCategoryName;
	private String yarnType;
	private String collectionName;
	private String fiber;
	private String gearLine;
	private String urlStyleImage;
	private String urlStyleImage2;
	private CustomerPolyPm customerPolyPm;
	
	@Id
	@Column(name="StyleID", nullable=false, unique=true)
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	
	@Column(name="StyleNumber")
	public String getStyleNumber() {
		return styleNumber;
	}
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	
	@Column(name="SeasonName")
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	
	@Column(name="SilhouetteName")
	public String getSilhouetteName() {
		return silhouetteName;
	}
	public void setSilhouetteName(String silhouetteName) {
		this.silhouetteName = silhouetteName;
	}
	
	@Column(name="StyleName")
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	@Column(name="StatusID")
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	@Column(name="StatusName")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@Column(name="CustomerID")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Column(name="DivisionName")
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	@Column(name="StyleCategoryName")
	public String getStyleCategoryName() {
		return styleCategoryName;
	}
	public void setStyleCategoryName(String styleCategoryName) {
		this.styleCategoryName = styleCategoryName;
	}
	
	@Column(name="Pattern")
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(name="StyleSubcategoryName")
	public String getStyleSubCategoryName() {
		return styleSubCategoryName;
	}
	public void setStyleSubCategoryName(String styleSubCategoryName) {
		this.styleSubCategoryName = styleSubCategoryName;
	}
	
	@Column(name="YarnType")
	public String getYarnType() {
		return yarnType;
	}
	public void setYarnType(String yarnType) {
		this.yarnType = yarnType;
	}
	
	@Column(name="CollectionName")
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	@Column(name="Fiber")
	public String getFiber() {
		return fiber;
	}
	public void setFiber(String fiber) {
		this.fiber = fiber;
	}
	
	@Column(name="GearLine")
	public String getGearLine() {
		return gearLine;
	}
	public void setGearLine(String gearLine) {
		this.gearLine = gearLine;
	}
	
	@Column(name="URLStyleImage")
	public String getUrlStyleImage() {
		return urlStyleImage;
	}
	public void setUrlStyleImage(String urlStyleImage) {
		this.urlStyleImage = urlStyleImage;
	}
	
	@Column(name="URLStyleImage2")
	public String getUrlStyleImage2() {
		return urlStyleImage2;
	}
	public void setUrlStyleImage2(String urlStyleImage2) {
		this.urlStyleImage2 = urlStyleImage2;
	}
	
	@Transient
	public CustomerPolyPm getCustomerPolyPm() {
		return customerPolyPm;
	}
	public void setCustomerPolyPm(CustomerPolyPm customerPolyPm) {
		this.customerPolyPm = customerPolyPm;
	}
	
		

}
