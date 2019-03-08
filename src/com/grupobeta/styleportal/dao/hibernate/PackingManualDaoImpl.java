package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.PackingManualDao;
import com.grupobeta.styleportal.domain.PackingManual;

public class PackingManualDaoImpl extends AbstractHibernateDaoImpl<PackingManual, Long> implements PackingManualDao {
	
	public PackingManualDaoImpl() {
		super(PackingManual.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackingManual> loadAllPackingManualFrom(String customer, String season, String style) {
		List<PackingManual> resultados = new ArrayList<PackingManual>();
		String hql = "from PackingManual where polyCustomerCode = :customer and seasonName = :season and styleNumber = :style and status = true ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("customer", customer);
		query.setParameter("season", season);
		query.setParameter("style", style);
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<PackingManual>(query.list());
		}
		
		return resultados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackingManual> loadAllPackingManualFrom(String customer, String season) {
		List<PackingManual> resultados = new ArrayList<PackingManual>();
		String hql = "from PackingManual where polyCustomerCode = :customer and seasonName = :season and styleNumber is null and status = true ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("customer", customer);
		query.setParameter("season", season);
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<PackingManual>(query.list());
		}
		
		return resultados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackingManual> loadAllPackingManualFromStyle(String customer, String style) {
		List<PackingManual> resultados = new ArrayList<PackingManual>();
		String hql = "from PackingManual where polyCustomerCode = :customer and seasonName is null and styleNumber = :style and status = true ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("customer", customer);
		query.setParameter("style", style);
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<PackingManual>(query.list());
		}
		
		return resultados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackingManual> loadAllPackingManualFromFilter(String customer, String season, String style) {
		List<PackingManual> resultados = new ArrayList<PackingManual>();
		if(customer!=null || season!=null || style!=null) {
			String hql = "from PackingManual where  ";
			 
			if(customer!=null) {
				hql += "polyCustomerCode = :customer ";
				if(season!=null || style!=null) {
					hql += " and ";
				}
			}
			
			if(season!=null) {
				hql += "seasonName = :season ";
				if(style!=null) {
					hql += " and ";
				}
			}
			
			if(style!=null) {
				hql += " styleNumber = :style ";
			}
			
			
			
			Query query = getSession().createQuery(hql);
			
			if(customer!=null) {
				query.setParameter("customer", customer);
			}
			
			if(season!=null) {
				query.setParameter("season", season);
			}
			
			if(style!=null) {
				query.setParameter("style", style);
			}
			
			
			if(!query.list().isEmpty()) {
				resultados = new ArrayList<PackingManual>(query.list());
			}
		
		}
		
		return resultados;
	}

}
