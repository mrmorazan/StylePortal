package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.CustomerPolyPm;

public interface CustomerPolyPmDao extends Dao<CustomerPolyPm, String> {

	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives);
	
}
