package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.SeasonPolyPm;

public interface SeasonPolyPmDao extends Dao<SeasonPolyPm, Integer> {

	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style);
	
}
