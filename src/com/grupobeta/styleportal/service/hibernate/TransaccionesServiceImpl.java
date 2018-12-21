package com.grupobeta.styleportal.service.hibernate;

import java.util.List;

import com.grupobeta.styleportal.dao.CustomerPolyPmDao;
import com.grupobeta.styleportal.dao.SeasonPolyPmDao;
import com.grupobeta.styleportal.dao.StylePolyPmDao;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.service.TransaccionesService;

public class TransaccionesServiceImpl extends AbstractHibernateServiceImpl implements TransaccionesService {
	private CustomerPolyPmDao customerPolyPmDao;
	private StylePolyPmDao stylePolyPmDao;
	private SeasonPolyPmDao seasonPolyPmDao;
	
	public SeasonPolyPmDao getSeasonPolyPmDao() {
		return seasonPolyPmDao;
	}

	public void setSeasonPolyPmDao(SeasonPolyPmDao seasonPolyPmDao) {
		this.seasonPolyPmDao = seasonPolyPmDao;
	}

	public StylePolyPmDao getStylePolyPmDao() {
		return stylePolyPmDao;
	}

	public void setStylePolyPmDao(StylePolyPmDao stylePolyPmDao) {
		this.stylePolyPmDao = stylePolyPmDao;
	}

	public CustomerPolyPmDao getCustomerPolyPmDao() {
		return customerPolyPmDao;
	}

	public void setCustomerPolyPmDao(CustomerPolyPmDao customerPolyPmDao) {
		this.customerPolyPmDao = customerPolyPmDao;
	}

	@Override
	public List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives) {
		return getCustomerPolyPmDao().loadAllCustomerActivesPolyPm(customerCode, inactives);
	}

	@Override
	public List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style,
			String season) {
		return getStylePolyPmDao().loadAllStylePolyPmFromCustomerStyleSeason(customerPolyPm, style, season);
	}

	@Override
	public List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style) {
		return getSeasonPolyPmDao().loadAllSeasonPolyPmFromStyle(style);
	}

	@Override
	public StylePolyPm loadStylePolyPm(int styleId, String season) {
		
		
		return getStylePolyPmDao().loadStylePolyPm(styleId, season);
	}
	
	
	
	
	
}