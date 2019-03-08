package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.ImagenTallaBAM;

public interface ImagenTallaBAMDao extends Dao<ImagenTallaBAM, Integer> {
	
	@Transactional(value="transactionManagerBAM", rollbackFor = { GBException.class, GBMultipleException.class })
	List<ImagenTallaBAM> loadAllImagenesTallaBAM(ImagenBam imagenBam);
	
}
