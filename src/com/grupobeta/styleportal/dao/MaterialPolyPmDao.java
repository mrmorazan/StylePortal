package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public interface MaterialPolyPmDao extends Dao<MaterialPolyPm, Integer> {
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<MaterialPolyPm> loadListMaterialPolyPmFromCategory(StylePolyPm style, int categoryId);

}
