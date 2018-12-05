package com.grupobeta.styleportal.service.hibernate;

import java.util.List;

import com.grupobeta.styleportal.dao.CustomerPolyPmDao;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.service.TransaccionesService;

public class TransaccionesServiceImpl extends AbstractHibernateServiceImpl implements TransaccionesService {
	private CustomerPolyPmDao customerPolyPmDao;

	public CustomerPolyPmDao getCustomerPolyPmDao() {
		return customerPolyPmDao;
	}

	public void setCustomerPolyPmDao(CustomerPolyPmDao customerPolyPmDao) {
		this.customerPolyPmDao = customerPolyPmDao;
	}

	@Override
	public List<CustomerPolyPm> loadAllCustomerActivesPolyPm() {
		return getCustomerPolyPmDao().loadAllCustomerActivesPolyPm();
	}
	
	
	
	
	
}