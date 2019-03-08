package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ArteBam;

public interface ArteBamDao extends Dao<ArteBam, Long> {
	
	@Transactional(value="transactionManagerBAM", rollbackFor = { GBException.class, GBMultipleException.class })
	List<ArteBam> loadArtesFromBam(String customerCode, String estilo, String season);
}
