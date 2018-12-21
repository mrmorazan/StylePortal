package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.CustomerPolyPmDao;
import com.grupobeta.styleportal.domain.CustomerPolyPm;

public class CustomerPolyPmDaoImpl extends AbstractHibernateDaoImpl<CustomerPolyPm, String> implements CustomerPolyPmDao {

	public CustomerPolyPmDaoImpl() {
		super(CustomerPolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives) {
		List<CustomerPolyPm> resultados = new ArrayList<CustomerPolyPm>();
		
		String sql = "SELECT " +
				"a.AddressID " +
				", a.CompanyNumber " +
				", 'C'+a.CompanyNumber 'PortalCode' " +
				", a.CompanyName " +
				"FROM Addresses a " +
				"WHERE a.AddrCategoryID = 2 ";
				
				if(!inactives) {
					sql +="AND a.StatusID = 30 ";
				} 
				
				if(customerCode!=null) {
					sql +="AND a.CompanyNumber like :customerCode ";
				} 
				
				sql +="ORDER BY a.CompanyNumber";
		
		Query query = getSession().createSQLQuery(sql).addEntity(CustomerPolyPm.class);
		
		if(customerCode!=null) {
			query.setParameter("customerCode", "%"+customerCode+"%");
		} 
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<CustomerPolyPm>(query.list());
		}
		
		return resultados;
	}
	
}
