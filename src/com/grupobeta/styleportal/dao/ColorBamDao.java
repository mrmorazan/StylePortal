package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ColorBam;
import com.grupobeta.styleportal.domain.ImagenBam;

public interface ColorBamDao extends Dao<ColorBam, Integer> {

	@Transactional(value="transactionManagerBAM", rollbackFor = { GBException.class, GBMultipleException.class })
	List<ColorBam> loadAllColorFromImage(ImagenBam imagenBam);
	
}
