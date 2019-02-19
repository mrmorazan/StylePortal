package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.WorkTaskPolyPmDao;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.WorkTaskPolyPm;

public class WorkTaskPolyPmDaoImpl extends AbstractHibernateDaoImpl<WorkTaskPolyPm, Integer> implements WorkTaskPolyPmDao {

	public WorkTaskPolyPmDaoImpl() {
		super(WorkTaskPolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkTaskPolyPm> loadWorkTaskFromStyleSeason(StylePolyPm style) {
		List<WorkTaskPolyPm> resultados = new ArrayList<WorkTaskPolyPm>();
		
		Query query = getSession().createSQLQuery("EXEC GB.pr_GetStyleWorkFlow @Style = :style ,@Season = :season ").addEntity(WorkTaskPolyPm.class);
		query.setParameter("style", style.getStyleNumber());
		query.setString("season", style.getSeasonName());
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<WorkTaskPolyPm>(query.list());
		}
		
		return resultados;
	}
	
}
