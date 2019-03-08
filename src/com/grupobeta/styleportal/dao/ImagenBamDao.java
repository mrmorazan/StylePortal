package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ArteBam;
import com.grupobeta.styleportal.domain.ImagenBam;

public interface ImagenBamDao extends Dao<ImagenBam, Long> {
	
	@Transactional(value="transactionManagerBAM", rollbackFor = { GBException.class, GBMultipleException.class })
	List<ImagenBam> loadAllImagenBamFromArte(ArteBam arteBam);

}
