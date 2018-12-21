package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public interface StylePolyPmDao extends Dao<StylePolyPm, Integer> {
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style, String season);
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	StylePolyPm loadStylePolyPm(int styleId, String season);
}
