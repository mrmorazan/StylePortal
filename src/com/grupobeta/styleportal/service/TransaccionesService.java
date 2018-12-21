package com.grupobeta.styleportal.service;

import java.util.List;

import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public interface TransaccionesService extends Service {

	List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives);
	
	List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style, String season);
	
	List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style);
	
	StylePolyPm loadStylePolyPm(int styleId, String season);
}
