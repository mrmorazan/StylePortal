package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.StylePolyPmDao;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class StylePolyPmDaoImpl extends AbstractHibernateDaoImpl<StylePolyPm, Integer> implements StylePolyPmDao {

	public StylePolyPmDaoImpl() {
		super(StylePolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style,
			String season) {
		List<StylePolyPm> resultados = new ArrayList<StylePolyPm>();

		if(season!=null) {
			Query query = getSession().getNamedQuery("POLYHEADERSTYLE");
			query.setParameter("customerId", customerPolyPm.getCustomerId());
			query.setParameter("styleNumber", style);
			query.setParameter("seasonName", season);
			if (!query.list().isEmpty()) {
				resultados = new ArrayList<StylePolyPm>(query.list());
			}
		} else {
			Query query = getSession().getNamedQuery("POLYHEADERSTYLE2");
			query.setParameter("customerId", customerPolyPm.getCustomerId());
			query.setParameter("styleNumber", style);
			if (!query.list().isEmpty()) {
				resultados = new ArrayList<StylePolyPm>(query.list());
			}
		}
		

		return resultados;
	}

	@Override
	public StylePolyPm loadStylePolyPm(int styleId, String season) {
		StylePolyPm stylePolyPm = null;
		
		String sql = "SELECT s.StyleID " 
					+ ",s.StyleNumber " + ",s1.SeasonName " + ",ss.SilhouetteName "
				+ ",s.StyleName " + ",sn.StatusID " + ",sn.StatusName " + ",s.CustomerID " + ",d.DivisionName "
				+ ",sc.StyleCategoryName " + ",s.Comments3 Pattern " + ",sc.StyleSubcategoryName " + ",s.Memo YarnType "
				+ ",sc1.CollectionName " + ",s.Memo2 Fiber " + ",ddv.DropDownValue GearLine "
				+ ",ISNULL(REPLACE('file://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d1.FilePath , '\\' , '/'),0) AS 'URLStyleImage' "
				+ ", d1.FilePath AS 'URLStyleImage2' "
				+ "FROM Styles s " + "LEFT JOIN Seasons s1 " + "ON s.SeasonID = s1.SeasonID "
				+ "LEFT JOIN StatusNames sn " + "ON s.StatusID = sn.StatusID " + "LEFT JOIN StyleSilhouettes ss "
				+ "ON s.SilhouetteID = ss.SilhouetteID " + "LEFT JOIN Divisions d " + "ON s.DivisionID = d.DivisionID "
				+ "LEFT JOIN StyleCategories sc " + "ON s.StyleCategoryID = sc.StyleCategoryID "
				+ "LEFT JOIN StyleCollections sc1 " + "ON s.CollectionID = sc1.CollectionID "
				+ "LEFT JOIN DropDownValues ddv " + "ON s.GearLineID = ddv.DropDownValueID " + "LEFT JOIN Addresses a "
				+ "ON s.CustomerID = a.AddressID "; 
		if(season!=null) {
		 sql += "LEFT JOIN Documents d1 "
					+ "ON d1.FolderPath = 'Styles\\' + LTRIM(RTRIM(s.StyleNumber)) + LTRIM(RTRIM(a.CompanyNumber)) + LTRIM(RTRIM(s1.SeasonName)) "
					+ "AND d1.[FileName] = LTRIM(RTRIM(s.StyleNumber)) " + "WHERE s.StyleID = :styleID ";
			
		} else {
			sql += "LEFT JOIN Documents d1 "
					+ "ON d1.FolderPath = 'Styles\\' + LTRIM(RTRIM(s.StyleNumber)) + LTRIM(RTRIM(a.CompanyNumber)) "
					+ "AND d1.[FileName] = LTRIM(RTRIM(s.StyleNumber)) " + "WHERE s.StyleID = :styleID ";
		}		
							
		
		Query query = getSession().createSQLQuery(sql).addEntity(StylePolyPm.class);
		query.setParameter("styleID", styleId);
	//	query.setMaxResults(1);
		
		stylePolyPm = (StylePolyPm) query.uniqueResult();
		
		return stylePolyPm;
	}

}
