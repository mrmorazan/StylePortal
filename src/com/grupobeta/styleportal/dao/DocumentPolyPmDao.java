package com.grupobeta.styleportal.dao;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.DocumentPolyPm;

public interface DocumentPolyPmDao extends Dao<DocumentPolyPm, Integer> {
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	DocumentPolyPm loadDocumentPolyPmComponent(String component);
}
