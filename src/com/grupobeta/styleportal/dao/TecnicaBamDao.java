package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.TecnicaBam;

public interface TecnicaBamDao extends Dao<TecnicaBam, Integer> {
	
	@Transactional(value="transactionManagerBAM", rollbackFor = { GBException.class, GBMultipleException.class })
	List<TecnicaBam> loadAllTecnicasFromImage(ImagenBam imagenBam);
	
}
