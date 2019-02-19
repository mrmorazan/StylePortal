package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.WorkTaskPolyPm;

public interface WorkTaskPolyPmDao extends Dao<WorkTaskPolyPm, Integer> {

	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<WorkTaskPolyPm> loadWorkTaskFromStyleSeason(StylePolyPm style);
	
}
