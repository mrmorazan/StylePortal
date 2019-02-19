package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public interface InstructionPolyPmDao extends Dao<InstructionPolyPm, Integer> {

	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	List<InstructionPolyPm> loadInstructionsForStyle(StylePolyPm style);
	
}
