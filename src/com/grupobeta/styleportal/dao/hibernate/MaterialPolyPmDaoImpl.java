package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.MaterialPolyPmDao;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class MaterialPolyPmDaoImpl extends AbstractHibernateDaoImpl<MaterialPolyPm, Integer> implements MaterialPolyPmDao {
	
	public MaterialPolyPmDaoImpl() {
		super(MaterialPolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialPolyPm> loadListMaterialPolyPmFromCategory(StylePolyPm style, int categoryId) {
		List<MaterialPolyPm> resultados = new ArrayList<MaterialPolyPm>();
		
		String sql= "SELECT " +
				"rm.RawMaterialID " +
				",rm.PartNumber " +
				",rm.Description " +
				",bp.BodyPart " +
				",cc.CategoryName " +
				",cc.ComponentCategoryID " +
				",REPLACE(cl.ComponentName, '/', '`_') ComponentName " +
				"FROM Styles s " +
				"LEFT JOIN Seasons s1 " +
				"ON s.SeasonID = s1.SeasonID " +
				"LEFT JOIN StyleDetails sd " +
				"ON s.StyleID = sd.StyleID " +
				"LEFT JOIN RawMaterials rm " +
				"ON sd.RawMaterialID = rm.RawMaterialID " +
				"LEFT JOIN ComponentLibrary cl " +
				"ON rm.ComponentID = cl.ComponentID " +
				"LEFT JOIN BodyParts bp " +
				"ON sd.BodyPartID = bp.BodyPartID " +
				"LEFT JOIN ComponentCategories cc " +
				"ON cl.ComponentCategoryID = cc.ComponentCategoryID " +
				"WHERE s.StyleNumber = :styleNumber "; 
				
		if(style.getSeasonName()!=null) {
			sql += "AND s1.SeasonName = :seasonName ";
		} else {
			sql += "AND s1.SeasonName is null ";
		}
				 
			sql += "AND cc.ComponentCategoryID = :category ";
			sql += "Order by bp.BodyPart ";
			
			Query query = getSession().createSQLQuery(sql).addEntity(MaterialPolyPm.class);
			query.setParameter("styleNumber", style.getStyleNumber());
			
			if(style.getSeasonName()!=null) {
				query.setParameter("seasonName", style.getSeasonName());
			}
			
			query.setParameter("category", categoryId);
			
			if(!query.list().isEmpty()) {
				resultados = new ArrayList<MaterialPolyPm>(query.list());
			}
		
		
		return resultados;
	}
	
	

}
