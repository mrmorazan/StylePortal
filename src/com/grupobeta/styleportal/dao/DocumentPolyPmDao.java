package com.grupobeta.styleportal.dao;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public interface DocumentPolyPmDao extends Dao<DocumentPolyPm, Integer> {
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	DocumentPolyPm loadDocumentPolyPmComponent(String component);
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	DocumentPolyPm loadDocumentPolyPmInstruction(StylePolyPm style, InstructionPolyPm instruction);
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	DocumentPolyPm loadDocumentPolyPmSpec(StylePolyPm style);
	
	@Transactional(value="transactionManagerPoly", rollbackFor = { GBException.class, GBMultipleException.class })
	DocumentPolyPm loadDocumentPolyPmTechPack(StylePolyPm style);
}
