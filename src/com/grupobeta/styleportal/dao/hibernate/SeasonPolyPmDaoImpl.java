package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.SeasonPolyPmDao;
import com.grupobeta.styleportal.domain.SeasonPolyPm;

public class SeasonPolyPmDaoImpl extends AbstractHibernateDaoImpl<SeasonPolyPm, Integer> implements SeasonPolyPmDao {

	public SeasonPolyPmDaoImpl() {
		super(SeasonPolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style) {
		List<SeasonPolyPm> resultados = new ArrayList<SeasonPolyPm>();
		
		String sql = "SELECT s1.SeasonID, s1.SeasonName " +
				"FROM Styles s " +
				"INNER JOIN Seasons s1 " +
				"ON s.SeasonID = s1.SeasonID " +
				"WHERE s.StyleNumber = :style";
		
		Query query = getSession().createSQLQuery(sql).addEntity(SeasonPolyPm.class);
		query.setParameter("style", style);
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<SeasonPolyPm>(query.list());
		}
		
		return resultados;
	}
	
	
	
	
}
